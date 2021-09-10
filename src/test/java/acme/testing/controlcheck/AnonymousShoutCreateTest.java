package acme.testing.controlcheck;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.RandomStringUtils;
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
	private final String pattern = "TPOIU-dd/MM/YY";
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final String author, final String text, final String link,final String budget,final String important,final int index) {
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		
		
		final String deadline = this.obtenerFechaActualPlusWeek();
		
		final String identification =this.obtenerPatternConFechaActual(this.pattern);


		//Modificar en el examen
		super.fillInputBoxIn("mocke.deadline", deadline);
		super.fillInputBoxIn("mocke.identification", identification);
		super.fillInputBoxIn("mocke.budget", budget);
		
		if(Boolean.parseBoolean(important)) {
			super.locateOne(By.id("mocke.important$proxy")).click();}

		
		
		
		super.clickOnSubmitButton("Shout!");
		
		super.clickOnMenu("Anonymous", "List shouts");
		
		super.checkColumnHasValue(index, 1, author);
		super.checkColumnHasValue(index, 2, text);
		super.checkColumnHasValue(index, 3, link);
		
		super.checkColumnHasValue(index, 4, deadline);
		super.checkColumnHasValue(index, 6, identification);
		super.checkColumnHasValue(index, 7, budget);
	
		
		
	}
	
	/**
	 * Click on Anonymous - Shout!, try to create a shout, check it is not created 
	 * because it have errors , finish.
	 * The error is the identification attribute. 
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative-identification.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegativeIdentification( final String author, final String text, final String link, final String identification,final String budget,final String important) {
		
	super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		
		final String deadline = this.obtenerFechaActualPlusWeek();

		//Modificar en el examen
		super.fillInputBoxIn("mocke.deadline", deadline);
		super.fillInputBoxIn("mocke.identification", identification);
		super.fillInputBoxIn("mocke.budget", budget);
		
		if(Boolean.parseBoolean(important)) {
			super.locateOne(By.id("mocke.important$proxy")).click();}
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist("mocke.identification");
	}
	
	/**
	 * Click on Anonymous - Shout!, try to create a shout, check it is not created 
	 * because it have errors , finish.
	 * The error is the deadline attribute. 
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative-deadline.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegativeDeadline( final String author, final String text, final String link, final String deadline,final String budget,final String important) {
		
	super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		
		final String identification = this.obtenerPatternConFechaActual(this.pattern);

		//Modificar en el examen
		super.fillInputBoxIn("mocke.deadline", deadline);
		super.fillInputBoxIn("mocke.identification", identification);
		super.fillInputBoxIn("mocke.budget", budget);
		
		if(Boolean.parseBoolean(important)) {
			super.locateOne(By.id("mocke.important$proxy")).click();}
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist("mocke.deadline");
	}
	
	/**
	 * Click on Anonymous - Shout!, try to create a shout, check it is not created 
	 * because it have errors , finish.
	 * The error is the budget attribute. 
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative-budget.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegativeBudget( final String author, final String text, final String link,final String budget,final String important) {
		
	super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		
		final String identification = this.obtenerPatternConFechaActual(this.pattern);
		final String deadline = this.obtenerFechaActualPlusWeek();

		//Modificar en el examen
		super.fillInputBoxIn("mocke.deadline", deadline);
		super.fillInputBoxIn("mocke.identification", identification);
		super.fillInputBoxIn("mocke.budget", budget);
		
		if(Boolean.parseBoolean(important)) {
			super.locateOne(By.id("mocke.important$proxy")).click();}
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist("mocke.budget");
	}
	
	/**
	 * Click on Anonymous - Shout!, try to create a shout, check it is not created 
	 * because it have errors , finish.
	 * The error is the author attribute. 
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative-author.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegativeAuthor( final String author, final String text, final String link,final String budget,final String important) {
		
	super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		
		final String identification = this.obtenerPatternConFechaActual(this.pattern);
		final String deadline = this.obtenerFechaActualPlusWeek();

		//Modificar en el examen
		super.fillInputBoxIn("mocke.deadline", deadline);
		super.fillInputBoxIn("mocke.identification", identification);
		super.fillInputBoxIn("mocke.budget", budget);
		
		if(Boolean.parseBoolean(important)) {
			super.locateOne(By.id("mocke.important$proxy")).click();}
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist("author");
	}
	
	/**
	 * Click on Anonymous - Shout!, try to create a shout, check it is not created 
	 * because it have errors , finish.
	 * The error is the text attribute. 
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative-text.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegativeText( final String author, final String text, final String link,final String budget,final String important) {
		
	super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		
		final String identification = this.obtenerPatternConFechaActual(this.pattern);
		final String deadline = this.obtenerFechaActualPlusWeek();

		//Modificar en el examen
		super.fillInputBoxIn("mocke.deadline", deadline);
		super.fillInputBoxIn("mocke.identification", identification);
		super.fillInputBoxIn("mocke.budget", budget);
		
		if(Boolean.parseBoolean(important)) {
			super.locateOne(By.id("mocke.important$proxy")).click();}
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist("text");
	}
	
	/**
	 * Click on Anonymous - Shout!, try to create a shout, check it is not created 
	 * because it have errors , finish.
	 * The error is the link attribute. 
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative-link.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegativeLink( final String author, final String text, final String link,final String budget,final String important) {
		
	super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		
		final String identification = this.obtenerPatternConFechaActual(this.pattern);
		final String deadline = this.obtenerFechaActualPlusWeek();

		//Modificar en el examen
		super.fillInputBoxIn("mocke.deadline", deadline);
		super.fillInputBoxIn("mocke.identification", identification);
		super.fillInputBoxIn("mocke.budget", budget);
		
		if(Boolean.parseBoolean(important)) {
			super.locateOne(By.id("mocke.important$proxy")).click();}
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist("link");
	}
	
	//Con esto automatizo que la fecha del momento siempre sea una semana a la fecha de creación
	public String obtenerFechaActualPlusWeek() {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/YY h:mm a");
		final LocalDateTime fechaactual = LocalDateTime.now().plusDays(10);
		final String fecha = fechaactual.format(formatter); 
		return fecha;
	}
	
	//Automatizo la variable unica y con pattern
	public String obtenerPatternConFechaActual(final String pattern) {
		final String año = Year.now().format(DateTimeFormatter.ofPattern("uu"));
		final int month = LocalDateTime.now().getMonthValue();
		final int day = LocalDateTime.now().getDayOfMonth();
		final String monthgood = month <10 ? "0"+month : String.valueOf(month);
		final String daygood = day <10 ? "0"+day : String.valueOf(day);
		
		final String ramdom = RandomStringUtils.randomAlphanumeric(1).toLowerCase();
		final String ramdom2 = RandomStringUtils.randomAlphanumeric(1).toLowerCase();
		final String ramdom3 = RandomStringUtils.randomAlphanumeric(1).toLowerCase();
		final String ramdom4 = RandomStringUtils.randomAlphanumeric(1).toLowerCase();

		String devuelvo = pattern;
		devuelvo = devuelvo.replace("MM", monthgood);
		devuelvo = devuelvo.replace("YY",año);
		devuelvo = devuelvo.replace("dd", daygood);
		devuelvo = devuelvo.replace("P", ramdom);
		devuelvo = devuelvo.replace("O", ramdom2);
		devuelvo = devuelvo.replace("I", ramdom3);
		devuelvo = devuelvo.replace("U", ramdom4);

		return devuelvo;
	}
}
