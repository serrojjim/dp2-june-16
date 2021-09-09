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
	private final String pattern = "TXLW:YY:MMdd";
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final String author, final String text, final String link,final String sergiolo4,final String sergiolo5,final int index) {
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		
		
		final String sergiolo3 = this.obtenerFechaActualPlusWeek();
		
		final String sergiolo2 =this.obtenerPatternConFechaActual(this.pattern);


		//Modificar en el examen
		super.fillInputBoxIn("sergiolo1.sergiolo3", sergiolo3);
		super.fillInputBoxIn("sergiolo1.sergiolo2", sergiolo2);
		super.fillInputBoxIn("sergiolo1.sergiolo4", sergiolo4);
		
		if(Boolean.parseBoolean(sergiolo5)) {
			super.locateOne(By.id("sergiolo1.sergiolo5$proxy")).click();}

		
		
		
		super.clickOnSubmitButton("Shout!");
		
		super.clickOnMenu("Anonymous", "List shouts");
		
		super.checkColumnHasValue(index, 1, author);
		super.checkColumnHasValue(index, 2, text);
		super.checkColumnHasValue(index, 3, link);
		
		super.checkColumnHasValue(index, 4, sergiolo3);
		super.checkColumnHasValue(index, 6, sergiolo2);
		super.checkColumnHasValue(index, 7, sergiolo4);
	
		
		
	}
	
	/**
	 * Click on Anonymous - Shout!, try to create a shout, check it is not created 
	 * because it have errors , finish.
	 * The error is the sergiolo2 attribute. 
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative-sergiolo2.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegativeSergiolo2( final String author, final String text, final String link, final String sergiolo2,final String sergiolo4,final String sergiolo5) {
		
	super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		
		final String sergiolo3 = this.obtenerFechaActualPlusWeek();

		//Modificar en el examen
		super.fillInputBoxIn("sergiolo1.sergiolo3", sergiolo3);
		super.fillInputBoxIn("sergiolo1.sergiolo2", sergiolo2);
		super.fillInputBoxIn("sergiolo1.sergiolo4", sergiolo4);
		
		if(Boolean.parseBoolean(sergiolo5)) {
			super.locateOne(By.id("sergiolo1.sergiolo5$proxy")).click();}
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist("sergiolo1.sergiolo2");
	}
	
	/**
	 * Click on Anonymous - Shout!, try to create a shout, check it is not created 
	 * because it have errors , finish.
	 * The error is the sergiolo3 attribute. 
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative-sergiolo3.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegativeSergiolo3( final String author, final String text, final String link, final String sergiolo3,final String sergiolo4,final String sergiolo5) {
		
	super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		
		final String sergiolo2 = this.obtenerPatternConFechaActual(this.pattern);

		//Modificar en el examen
		super.fillInputBoxIn("sergiolo1.sergiolo3", sergiolo3);
		super.fillInputBoxIn("sergiolo1.sergiolo2", sergiolo2);
		super.fillInputBoxIn("sergiolo1.sergiolo4", sergiolo4);
		
		if(Boolean.parseBoolean(sergiolo5)) {
			super.locateOne(By.id("sergiolo1.sergiolo5$proxy")).click();}
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist("sergiolo1.sergiolo3");
	}
	
	/**
	 * Click on Anonymous - Shout!, try to create a shout, check it is not created 
	 * because it have errors , finish.
	 * The error is the sergiolo4 attribute. 
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative-sergiolo4.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegativeSergiolo4( final String author, final String text, final String link,final String sergiolo4,final String sergiolo5) {
		
	super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		
		final String sergiolo2 = this.obtenerPatternConFechaActual(this.pattern);
		final String sergiolo3 = this.obtenerFechaActualPlusWeek();

		//Modificar en el examen
		super.fillInputBoxIn("sergiolo1.sergiolo3", sergiolo3);
		super.fillInputBoxIn("sergiolo1.sergiolo2", sergiolo2);
		super.fillInputBoxIn("sergiolo1.sergiolo4", sergiolo4);
		
		if(Boolean.parseBoolean(sergiolo5)) {
			super.locateOne(By.id("sergiolo1.sergiolo5$proxy")).click();}
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist("sergiolo1.sergiolo4");
	}
	
	/**
	 * Click on Anonymous - Shout!, try to create a shout, check it is not created 
	 * because it have errors , finish.
	 * The error is the author attribute. 
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-negative-author.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createNegativeAuthor( final String author, final String text, final String link,final String sergiolo4,final String sergiolo5) {
		
	super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		
		final String sergiolo2 = this.obtenerPatternConFechaActual(this.pattern);
		final String sergiolo3 = this.obtenerFechaActualPlusWeek();

		//Modificar en el examen
		super.fillInputBoxIn("sergiolo1.sergiolo3", sergiolo3);
		super.fillInputBoxIn("sergiolo1.sergiolo2", sergiolo2);
		super.fillInputBoxIn("sergiolo1.sergiolo4", sergiolo4);
		
		if(Boolean.parseBoolean(sergiolo5)) {
			super.locateOne(By.id("sergiolo1.sergiolo5$proxy")).click();}
		
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
	public void createNegativeText( final String author, final String text, final String link,final String sergiolo4,final String sergiolo5) {
		
	super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		
		final String sergiolo2 = this.obtenerPatternConFechaActual(this.pattern);
		final String sergiolo3 = this.obtenerFechaActualPlusWeek();

		//Modificar en el examen
		super.fillInputBoxIn("sergiolo1.sergiolo3", sergiolo3);
		super.fillInputBoxIn("sergiolo1.sergiolo2", sergiolo2);
		super.fillInputBoxIn("sergiolo1.sergiolo4", sergiolo4);
		
		if(Boolean.parseBoolean(sergiolo5)) {
			super.locateOne(By.id("sergiolo1.sergiolo5$proxy")).click();}
		
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
	public void createNegativeLink( final String author, final String text, final String link,final String sergiolo4,final String sergiolo5) {
		
	super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		
		final String sergiolo2 = this.obtenerPatternConFechaActual(this.pattern);
		final String sergiolo3 = this.obtenerFechaActualPlusWeek();

		//Modificar en el examen
		super.fillInputBoxIn("sergiolo1.sergiolo3", sergiolo3);
		super.fillInputBoxIn("sergiolo1.sergiolo2", sergiolo2);
		super.fillInputBoxIn("sergiolo1.sergiolo4", sergiolo4);
		
		if(Boolean.parseBoolean(sergiolo5)) {
			super.locateOne(By.id("sergiolo1.sergiolo5$proxy")).click();}
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist("link");
	}
	
	//Con esto automatizo que la fecha del momento siempre sea una semana a la fecha de creación
	public String obtenerFechaActualPlusWeek() {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/YY HH:mm a");
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
		
		final String ramdom = RandomStringUtils.randomAlphabetic(1).toLowerCase();
		final String ramdom2 = RandomStringUtils.randomAlphabetic(1).toLowerCase();
		final String ramdom3 = RandomStringUtils.randomAlphabetic(1).toLowerCase();

		String devuelvo = pattern;
		devuelvo = devuelvo.replace("MM", monthgood);
		devuelvo = devuelvo.replace("YY",año);
		devuelvo = devuelvo.replace("dd", daygood);
		devuelvo = devuelvo.replace("X", ramdom);
		devuelvo = devuelvo.replace("W", ramdom2);
		devuelvo = devuelvo.replace("L", ramdom3);

		return devuelvo;
	}
}
