package task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskDeleteService extends AcmePlannerTest{
	/**
	 * Sign in as a manager and delete a workplan with tasks associated.
	 * No errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/task/deletePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deleteTaskPositive(final int id, final int version,final String description, final String execution_period_initial_date
		, final String execution_period_final_date
		, final Boolean isPrivate, final String title,
		final String url, final String workload,
		final int user_account_id){
		super.signIn("Antonio", "Campuzano");

		super.clickOnMenu("Manager", "List tasks");

		super.clickOnListingRecord(id);

		super.clickOnSubmitButton("Delete");

		super.signOut();
	}
}
