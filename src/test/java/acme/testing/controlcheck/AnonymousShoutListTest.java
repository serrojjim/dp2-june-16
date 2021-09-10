package acme.testing.controlcheck;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutListTest extends AcmePlannerTest{
	
	
	/**
	    * As an anonymous user, list all the shouts that were created less than 
	    * a month ago sorted by date and check that every value is correct.
	    * No errors expected.
	    */
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listRecentShoutsByMoment(final int recordIndex, final String author, final String link, final String moment, final String text,
		final String deadline,final String important,final String identification,final String budget) {
		super.clickOnMenu("Anonymous", "List shouts");
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, link);
		super.checkColumnHasValue(recordIndex, 4, deadline);
		super.checkColumnHasValue(recordIndex, 5, important);
		super.checkColumnHasValue(recordIndex, 6, identification);
		super.checkColumnHasValue(recordIndex, 7, budget);

	}
	
}
