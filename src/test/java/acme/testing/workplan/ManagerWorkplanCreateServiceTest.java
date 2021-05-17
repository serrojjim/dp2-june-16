package acme.testing.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkplanCreateServiceTest extends AcmePlannerTest {

	/**
	 * Sign in as a manager, create a workplan, list all my workplans, show the recently created one
	 * and check that every value is correct.
	 * No errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/workplan/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	void createWorkplanManagerPositive(final int id, final int version, final String execution_period_initial_date, final String execution_period_final_date, final Boolean isPrivate, final String title,
		final int user_account_id, final String workload) {
		super.signIn("Antonio", "Campuzano");
		
		super.clickOnMenu("Manager", "Create workplans");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("executionPeriod.initialDate", execution_period_initial_date);
		super.fillInputBoxIn("executionPeriod.finalDate", execution_period_final_date);
		super.clickOnSubmitButton("Create");
		
		super.clickOnMenu("Manager", "List workplans");

		super.checkColumnHasValue(id, 0, title);
		super.checkColumnHasValue(id, 1, execution_period_final_date);
		super.checkColumnHasValue(id, 2, execution_period_initial_date);
		super.checkColumnHasValue(id, 3, "0.00");
		
		super.clickOnListingRecord(10);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("executionPeriod.initialDate", execution_period_initial_date);
		super.checkInputBoxHasValue("executionPeriod.finalDate", execution_period_final_date);

		super.signOut();
	}
	
	/**
	 * Signs in as a manager, tries to create a workplan with spam in the title and the initial date after the final date
	 * and check that it only has errors on the dates, because initially the workplan is private so it admits spam in the title.
	 * Thus, it wont be created.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/workplan/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	void createWorkplanManagerNegative(final int id, final int version, final String execution_period_initial_date, final String execution_period_final_date, final Boolean isPrivate, final String title,
		final int user_account_id, final String workload) {
		super.signIn("Antonio", "Campuzano");
		
		super.clickOnMenu("Manager", "Create workplans");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("executionPeriod.initialDate", execution_period_initial_date);
		super.fillInputBoxIn("executionPeriod.finalDate", execution_period_final_date);
		super.clickOnSubmitButton("Create");
		
		super.checkNotErrorsExist("title");
		super.checkErrorsExist("executionPeriod.initialDate");
		super.checkErrorsExist("executionPeriod.finalDate");

		super.signOut();
	}

}
