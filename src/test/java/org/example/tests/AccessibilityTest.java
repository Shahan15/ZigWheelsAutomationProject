package org.example.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.VerifyingHomePage;
import utils.*;

import static utils.ReportUtils.extent;

@Listeners(TestListener.class)
public class AccessibilityTest extends BaseTest {

    private VerifyingHomePage verifyingHomePage;
    private AccessibilityUtils accessibilityUtils;

    @BeforeTest
    public void setup() {
        ReportUtils.setUpExtentReport(this.getClass().getSimpleName());
        TestListener.setExtent(extent);

        verifyingHomePage = new VerifyingHomePage();
        accessibilityUtils = new AccessibilityUtils();
    }


    @DataProvider(name = "pageDataProvider")
    public Object[][] pageDataProvider() {
        return new Object[][]{
                {"HomePage"},
                {"upcomingBikesPage"},
                {"CarsPage"}
        };
    }

    @Test(dataProvider = "pageDataProvider")
    public void testAccessibilityForPages(String pageName) {
        String testName = "AccessibilityTest_" + pageName; // Create a dynamic test name

        // Dynamically create a test node for the current page
        ExtentTest test = ReportUtils.createTest(testName);

        // Log test start
        test.log(Status.INFO, "Starting accessibility test for: " + pageName);

        // Bring window into focus
        ((JavascriptExecutor) Base.getDriver()).executeScript("window.focus();");

        // Navigate to the page
        NavigationUtils.navigateToTestingSite(pageName);
        verifyingHomePage.clickingCookieConsentBtn();

        // Run accessibility checks and log results
        accessibilityUtils.axeHome(test, pageName);

        // Log test completion
        test.pass("Accessibility test completed successfully for: " + pageName);
    }


}
