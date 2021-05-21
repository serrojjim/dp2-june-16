package acme.testing.manager.authenticatedAmanager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerButtonTest extends AcmePlannerTest{
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/Manager/managerButton.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void ManagerButton(final String username, final String password) {
		
		super.signIn("Authenticated", "Authenticated");

		super.clickOnMenu("Account", "Become a manager");
		
		super.clickOnSubmitButton("Confirm");
		
		super.check
//		super.fillInputBoxIn("text", text);
//		super.fillInputBoxIn("link", link);
//		
//		super.clickOnSubmitButton("Shout!");
//		
//		super.clickOnMenu("Anonymous", "List shouts");
//		
//		super.checkColumnHasValue(0, 1, author);
//		super.checkColumnHasValue(0, 2, text);
//		super.checkColumnHasValue(0, 3, link);
		
	}

}
