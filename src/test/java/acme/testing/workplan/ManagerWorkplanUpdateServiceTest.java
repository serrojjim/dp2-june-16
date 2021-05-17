
package acme.testing.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import acme.testing.AcmePlannerTest;

public class ManagerWorkplanUpdateServiceTest extends AcmePlannerTest {

	/**
	 * Sign in as a manager, show a workplan, modify some fields, update them
	 * and check that every value is correct. No errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/workplan/update/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	void updateWorkplanManagerPositive(final int id, final int version, final String execution_period_final_date, final String execution_period_initial_date,
		final Boolean isPrivate, final String title, final int user_account_id, final String workload) {
		super.signIn("Antonio", "Campuzano");

		super.clickOnMenu("Manager", "List workplans");

		super.checkColumnHasValue(id, 0, title);
		super.checkColumnHasValue(id, 1, execution_period_final_date);
		super.checkColumnHasValue(id, 2, execution_period_initial_date);
		super.checkColumnHasValue(id, 3, workload);

		super.clickOnListingRecord(id);

		super.fillInputBoxIn("title", "Prueba de actualizacion");
		super.fillInputBoxIn("executionPeriod.initialDate", "8/12/22 1:00 AM");
		super.fillInputBoxIn("executionPeriod.finalDate", "12/12/22 2:00 AM");
		
		final By optionLocator = By.xpath(String.format("//*[@id=\"task\"]/option[11]"));
		final WebElement option = super.locateOne(optionLocator);
		option.click();
		
		super.clickOnSubmitButton("Update");
		
		super.checkColumnHasValue(id, 0, "Prueba de actualizacion");
		super.checkColumnHasValue(id, 1, "12/12/22 2:00 AM");
		super.checkColumnHasValue(id, 2, "5/11/21 8:00 AM");
		super.checkColumnHasValue(id, 3, "42.00");
		
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

		super.clickOnListingRecord(id);

		super.fillInputBoxIn("title", "sex sex sex");
		super.fillInputBoxIn("executionPeriod.initialDate", "invalid format date");
		super.fillInputBoxIn("executionPeriod.finalDate", "invalid format date");
		
//		final By optionLocator = By.xpath(String.format("//*[@id=\"task\"]/option[11]"));
//		final WebElement option = super.locateOne(optionLocator);
//		option.click();
		
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist("title");
		super.checkErrorsExist("executionPeriod.initialDate");
		super.checkErrorsExist("executionPeriod.finalDate");
		
		super.signOut();
	}

}
