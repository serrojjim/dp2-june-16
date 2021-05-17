package acme.testing.anonymous.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousWorkplanShowTest extends AcmePlannerTest{

    // Internal state ---------------------------------------------------------

    // Lifecycle management ---------------------------------------------------

    // Test cases -------------------------------------------------------------

    /**
    * As an anonymous user, show the details of workplan and check that every value is correct.
    * No errors expected.
    */
    @ParameterizedTest
    @CsvFileSource(resources = "/anonymous/workplan/show.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10)
    void showWorkplanManagerPositive(final int recordIndex, final String execution_period_final_date, final String execution_period_initial_date, final String title, final String workload) {
        super.clickOnMenu("Anonymous", "List workplans by Workload");

        super.clickOnListingRecord(recordIndex);
 
        
        super.checkInputBoxHasValue("title", title);
        super.checkInputBoxHasValue("executionPeriod.initialDate", execution_period_initial_date);
        super.checkInputBoxHasValue("executionPeriod.finalDate", execution_period_final_date);
        super.checkInputBoxHasValue("workload", workload);
    }
}