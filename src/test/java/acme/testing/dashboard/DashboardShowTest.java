package acme.testing.dashboard;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class DashboardShowTest extends AcmePlannerTest {
	
	@Test
	public void showPositive() {		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Dashboard");
		
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[1]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[2]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[3]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[4]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[5]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[6]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[7]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[8]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[9]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[10]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[11]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[1]/tbody/tr[12]/td[normalize-space(text()) = '']"));
		
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[1]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[2]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[3]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[4]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[5]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[6]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[7]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[8]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[9]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[10]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[11]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[12]/td[normalize-space(text()) = '']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr[13]/td[normalize-space(text()) = '']"));
		super.signOut();
	}

}
