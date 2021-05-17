
package acme.testing.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class ManagerWorkplanUpdateServiceTest extends AcmePlannerTest {

	/**
	 * Sign in as a manager, create a workplan, list all my workplans, show the recently created one
	 * and check that every value is correct.
	 * No errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/workplan/update/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	void updateWorkplanManagerPositive(final int id, final int version, final String execution_period_final_date, final String execution_period_initial_date, final Boolean isPrivate, final String title, final int user_account_id, final String workload) {
		super.signIn("Antonio", "Campuzano");

		super.clickOnMenu("Manager", "List workplans");

		super.checkColumnHasValue(id, 0, title);
		super.checkColumnHasValue(id, 1, execution_period_final_date);
		super.checkColumnHasValue(id, 2, execution_period_initial_date);
		super.checkColumnHasValue(id, 3, workload);

		super.clickOnListingRecord(id);

		
		//Rellenar con valores 
		super.fillInputBoxIn("title", "Prueba de actualizacion");
		super.fillInputBoxIn("executionPeriod.initialDate", "");
		super.fillInputBoxIn("executionPeriod.finalDate", "");
		
		//No estoy seguro de que sea asi
		super.fill(By.id("task"), "3");

		super.signOut();
	}

	/**
	 * Signs in as a manager, tries to create a public workplan with spam in the title and the initial date after the final date
	 * and check that it has errors on the title and on the dates. Thus, it wont be created.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/workplan/update/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	void updateWorkplanManagerNegative(final int id, final int version, final String execution_period_final_date, final String execution_period_initial_date, final Boolean isPrivate, final String title, final int user_account_id, final String workload) {
		super.signIn("Antonio", "Campuzano");

		super.clickOnMenu("Manager", "List workplans");

		super.checkColumnHasValue(id, 0, title);
		super.checkColumnHasValue(id, 1, execution_period_final_date);
		super.checkColumnHasValue(id, 2, execution_period_initial_date);
		super.checkColumnHasValue(id, 3, workload);

		super.clickOnListingRecord(id);

		//Podemos probar o vacio o con palabras spam
		super.fillInputBoxIn("title", " ");
		// Una fecha de inicio posterior a la de fin para que salta error
		super.fillInputBoxIn("executionPeriod.initialDate", "");
		super.fillInputBoxIn("executionPeriod.finalDate", "");
		// Una tarea random que sea privada para que al intentar meterselo a una publica reviente tambien
		super.fill(By.id("task"), "3");

		super.signOut();
	}

}
