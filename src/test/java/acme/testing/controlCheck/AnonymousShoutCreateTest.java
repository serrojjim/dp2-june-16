package acme.testing.controlCheck;

import java.time.LocalDate;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest{

	
	/**
	 * Click on Anonymous - Shout!, create a shout, list all shouts, show the recently created one
	 * and check that every value is correct.
	 * No errors expected.
	 */
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final String author, final String text, final String link, String sergiolo2,  final String sergiolo4, final String sergiolo5,final int index) {
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		
				
		sergiolo2 = this.getDateForPattern(sergiolo2);
		
		super.fillInputBoxIn("sergiolo1.sergiolo2", sergiolo2);
		
		
		
		final String sergiolo3 = this.getDateInAWeek();
		super.fillInputBoxIn("sergiolo1.sergiolo3",sergiolo3);
	
		
		super.fillInputBoxIn("sergiolo1.sergiolo4", sergiolo4);
		
		if(Boolean.parseBoolean(sergiolo5)) {
			super.locateOne(By.id("sergiolo1.sergiolo5$proxy")).click();

		}
		
		
		super.clickOnSubmitButton("Shout!");
		
		super.clickOnMenu("Anonymous", "List shouts");
		
		super.checkColumnHasValue(index, 1, author);
		super.checkColumnHasValue(index, 2, text);
		super.checkColumnHasValue(index, 3, link);
		super.checkColumnHasValue(index, 4, sergiolo2);
		super.checkColumnHasValue(index, 5, sergiolo3);
		super.checkColumnHasValue(index, 6,sergiolo4);
		
	}
	
	/**
	 * Click on Anonymous - Shout!, try to create a shout, check it is not created 
	 * because it have errors, finish.
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative-sergiolo2.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegativeSergiolo2(final String author, final String text, final String link,final String sergiolo2, final String sergiolo4) {
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("sergiolo1.sergiolo2", sergiolo2);		
		super.fillInputBoxIn("sergiolo1.sergiolo3",this.getDateInAWeek());
		super.fillInputBoxIn("sergiolo1.sergiolo4", sergiolo4);
		
		
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist("sergiolo1.sergiolo2");

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative-sergiolo3.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegativeSergiolo3(final String author, final String text, final String link,final String sergiolo4, final String sergiolo3) {
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("sergiolo1.sergiolo2", this.getDateForPattern("/11/aa"));		
		super.fillInputBoxIn("sergiolo1.sergiolo3",sergiolo3);
		super.fillInputBoxIn("sergiolo1.sergiolo4", sergiolo4);
		
		
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist("sergiolo1.sergiolo3");

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative-sergiolo4.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegativeSergiolo4(final String author, final String text, final String link,final String sergiolo4) {
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("sergiolo1.sergiolo2", this.getDateForPattern("/11/aa"));		
		super.fillInputBoxIn("sergiolo1.sergiolo3",this.getDateInAWeek());
		super.fillInputBoxIn("sergiolo1.sergiolo4", sergiolo4);
		
		
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist("sergiolo1.sergiolo4");

	}
	
	public String getDateInAWeek() {
		
		final String days = String.valueOf(LocalDate.now().plusWeeks(2).getDayOfMonth());
		final String month = String.valueOf(LocalDate.now().plusWeeks(2).getMonthValue());
		final String year = String.valueOf(LocalDate.now().plusWeeks(2).getYear()).substring(2,4);
		return String.format("%s/%s/%s 10:00 PM", month,days,year);
	}
	
	public String getDateForPattern(final String sergiolo2) {
		
		String days = String.valueOf(LocalDate.now().getDayOfMonth());
		String month = String.valueOf(LocalDate.now().getMonthValue());
		final String year = String.valueOf(LocalDate.now().getYear());
		
		if(days.length()==1) {
			days = "0" +days;
		}
		
		if(month.length()==1) {
			month = "0" +month;
		}
		
		return year.substring(2, 4)+month+days+sergiolo2;
	}
}
