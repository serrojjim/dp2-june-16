
package acme.testing.dashboard;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class DashboardShowTest extends AcmePlannerTest {

	/**
	 * Sign is as an admin, check initial dashboard values, sign out, sign in as a manager, 
	 * delete both a task and a workplan, sign out and sign in again as an admin and check
	 * updated values of the dashboard.
	 * No errors expected
	 */

	@Test
	public void showPositive() {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Dashboard");

		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[1]/td[normalize-space(text()) = '14']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[2]/td[normalize-space(text()) = '6']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[3]/td[normalize-space(text()) = '10']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[4]/td[normalize-space(text()) = '10']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[5]/td[normalize-space(text()) = '33.90']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[6]/td[normalize-space(text()) = '25.29']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[7]/td[normalize-space(text()) = '0']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[8]/td[normalize-space(text()) = '69']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[9]/td[normalize-space(text()) = '93.95']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[10]/td[normalize-space(text()) = '192.67']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[11]/td[normalize-space(text()) = '0.00']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[12]/td[normalize-space(text()) = '750.00']"));

		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[1]/td[normalize-space(text()) = '9']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[2]/td[normalize-space(text()) = '6']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[3]/td[normalize-space(text()) = '5']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[4]/td[normalize-space(text()) = '10']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[5]/td[normalize-space(text()) = '269.87']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[6]/td[normalize-space(text()) = '230.48']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[7]/td[normalize-space(text()) = '0']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[8]/td[normalize-space(text()) = '622']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[9]/td[normalize-space(text()) = '120.20']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[10]/td[normalize-space(text()) = '216.33']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[11]/td[normalize-space(text()) = '0.00']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[12]/td[normalize-space(text()) = '750.00']"));

		super.signOut();

		super.signIn("Sergio", "Rojas");

		super.clickOnMenu("Manager", "List workplans");
		super.clickOnListingRecord(0);
		super.clickOnSubmitButton("Delete workplan");

		super.clickOnMenu("Manager", "List tasks");
		super.clickOnListingRecord(0);
		super.clickOnSubmitButton("Delete");

		super.signOut();

		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Dashboard");

		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[1]/td[normalize-space(text()) = '13']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[2]/td[normalize-space(text()) = '6']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[3]/td[normalize-space(text()) = '9']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[4]/td[normalize-space(text()) = '10']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[5]/td[normalize-space(text()) = '35.21']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[6]/td[normalize-space(text()) = '25.27']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[7]/td[normalize-space(text()) = '0']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[8]/td[normalize-space(text()) = '69']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[9]/td[normalize-space(text()) = '98.89']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[10]/td[normalize-space(text()) = '196.43']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[11]/td[normalize-space(text()) = '0.00']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[12]/td[normalize-space(text()) = '750.00']"));

		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[1]/td[normalize-space(text()) = '8']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[2]/td[normalize-space(text()) = '6']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[3]/td[normalize-space(text()) = '5']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[4]/td[normalize-space(text()) = '9']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[5]/td[normalize-space(text()) = '259.86']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[6]/td[normalize-space(text()) = '235.40']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[7]/td[normalize-space(text()) = '0']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[8]/td[normalize-space(text()) = '622']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[9]/td[normalize-space(text()) = '107.32']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[10]/td[normalize-space(text()) = '218.29']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[11]/td[normalize-space(text()) = '0.00']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[12]/td[normalize-space(text()) = '750.00']"));

		super.signOut();
	}
	
	
	/**
	 * Try to show the dashboard as an anonymous principal.
	 * Panic expected.
	 */
	@Test
	public void showNegative() {
			super.navigate("/administrator/dashboard/show", "");
			super.checkPanicExists();
	}
	
	

}
