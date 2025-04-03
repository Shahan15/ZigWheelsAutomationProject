package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features/",
        glue = {"stepdefinitions", "hooks"}, //Specifies the package containing the step definitions.
        tags = "@carScraping",
        plugin = {"pretty", "html:src/test/resources/CucumberReports/cucumber-reports"}
        //this allows generation of reports
)

public class TestRunner extends AbstractTestNGCucumberTests {

}


//TestRunner is entry point for running feature files
//
