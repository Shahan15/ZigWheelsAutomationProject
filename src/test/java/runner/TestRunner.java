package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features/",
        glue = {"stepdefinitions", "hooks"},
//        tags = "@bikeScraping",
        plugin = {"pretty", "html:src/test/resources/CucumberReports/cucumber-reports","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
        //this allows generation of reports
)

public class TestRunner extends AbstractTestNGCucumberTests {

}


//TestRunner is entry point for running feature files
//
