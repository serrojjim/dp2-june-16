package acme.testing.workplan;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

import acme.features.manager.workplan.ManagerWorkplanRepository;
import acme.testing.AcmeTest;

public class ManagerWorkplanTest extends AcmeTest {

	// Internal state ---------------------------------------------------------

	@Autowired
	ManagerWorkplanRepository managerWorkplanRepository;
	
	// Lifecycle management ---------------------------------------------------

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();

		super.setBaseCamp("http", "localhost", "8080", "/Acme-Planner", "/master/welcome", "?language=en&debug=true");
		super.setAutoPausing(false);

		this.signIn("administrator", "administrator");
		super.clickAndGo(By.linkText("Administrator"));
		super.clickAndGo(By.linkText("Populate DB (initial)"));
		super.checkAlertExists(true);
		this.signOut();
	}

	// Test cases -------------------------------------------------------------

	
	/**
	 * Sign in as a manager, list all my workplans and check that every value is correct.
	 * No errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/workplan/listMinePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	void listMineWorkplanManagerPositive(final int id, final int version, final String execution_period_initial_date, final String execution_period_final_date, final Boolean isPrivate, final String title,
		final int user_account_id, final String workload) {
		super.signIn("Antonio", "Campuzano");
		
		super.clickOnMenu("Manager", "List workplans");
		
		super.checkColumnHasValue(id, 0, title);
		super.checkColumnHasValue(id, 1, execution_period_initial_date);
		super.checkColumnHasValue(id, 2, execution_period_final_date);
		super.checkColumnHasValue(id, 3, workload);

		super.signOut();
	}
	
	/**
	 * The list-mine feature has no negative tests that can be done
	 */
	void listMineWorkplanManagerNegative() {
	}
	
	/**
	 * Sign in as a manager, create a workplan, list all my workplans, show the recently created one
	 * and check that every value is correct.
	 * No errors expected.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/workplan/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
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
	 * Sign in as a manager, try create a workplan with some erroneous fields
	 * and check that it has errors, so it wont be created.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/workplan/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	void createWorkplanManagerNegative(final int id, final int version, final String execution_period_initial_date, final String execution_period_final_date, final Boolean isPrivate, final String title,
		final int user_account_id, final String workload) {
		super.signIn("Antonio", "Campuzano");
		
		super.clickOnMenu("Manager", "Create workplans");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("executionPeriod.initialDate", execution_period_initial_date);
		super.fillInputBoxIn("executionPeriod.finalDate", execution_period_final_date);
		super.clickOnSubmitButton("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	// Ancillary methods ------------------------------------------------------

}
