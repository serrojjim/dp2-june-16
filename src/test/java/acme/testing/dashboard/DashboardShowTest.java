package acme.testing.dashboard;

import org.junit.jupiter.api.Test;

import acme.testing.AcmePlannerTest;

public class DashboardShowTest extends AcmePlannerTest {
	
	@Test
	public void showPositive() {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Dashboard");
		
		super.signOut();
	}

}
