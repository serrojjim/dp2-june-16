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

	@ParameterizedTest
	@CsvFileSource(resources = "/workplan/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	void listWorkplanManagerPositive(final int id, final int version, final String execution_period_initial_date, final String execution_period_final_date, final Boolean isPrivate, final String title,
		final int user_account_id, final String workload) {
		super.signIn("Antonio", "Campuzano");
		
		super.clickOnMenu("Manager", "List workplans");
		
		super.checkColumnHasValue(id, 0, title);
		super.checkColumnHasValue(id, 1, execution_period_initial_date);
		super.checkColumnHasValue(id, 2, execution_period_final_date);
		super.checkColumnHasValue(id, 3, workload);

		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
