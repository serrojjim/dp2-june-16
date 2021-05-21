package acme.testing.anonymous.task;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskShowService extends AcmePlannerTest {

	// Test cases -------------------------------------------------------------

	
	/**
	 * Sign in as a Anonymous, create a task, list all my task, show the recently created one
	 * and check that every value is correct.
	 * No errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/task/showPositiveAnonymous.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	void ShowTaskAnonymousPositive(final int id, final int version,final String description, final String execution_period_final_date
		, final String execution_period_initial_date
		, final Boolean isPrivate, final String title,
		final String url, final String workload,
		final int user_account_id) {
			
			super.clickOnMenu("Anonymous", "List tasks");

			super.checkColumnHasValue(id, 0, title);
			super.checkColumnHasValue(id, 2, execution_period_final_date);
			super.checkColumnHasValue(id, 1, execution_period_initial_date);
			
			super.clickOnListingRecord(id);

			super.checkInputBoxHasValue("title", title);
			super.checkInputBoxHasValue("executionPeriod.initialDate", execution_period_initial_date);
			super.checkInputBoxHasValue("executionPeriod.finalDate", execution_period_final_date);

		}
	
	// Wont work until super.checkPanicExists() is implemented in the framework
//	/**
//	 * Signs in as a anonymous, tries to show a non existing task and expect a panic.
//	 */
//	@ParameterizedTest
//	@CsvFileSource(resources = "/task/anonymousShowNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
//	@Order(20)
//	void showWorkplanManagerNegative(final int id, final int version, final String execution_period_initial_date, final String execution_period_final_date, final Boolean isPrivate, final String title,
//		final int user_account_id, final String workload) {
//		
//		super.navigate("/anonymous/task/show?", "id=" + id);
//		super.checkPanicExists();
//		
//	}
	
	
	
}