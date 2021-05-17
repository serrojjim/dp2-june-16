package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest{

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final String author, final String text, final String link) {
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmitButton("Shout!");
		
		super.clickOnMenu("Anonymous", "List shouts");
		
		super.checkColumnHasValue(0, 1, author);
		super.checkColumnHasValue(0, 2, text);
		super.checkColumnHasValue(0, 3, link);
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegative( final String author, final String text, final String link) {
		
	super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
	}
}
