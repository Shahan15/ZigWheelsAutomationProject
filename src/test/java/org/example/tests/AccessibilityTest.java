package org.example.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.NavigatingToBikes;
import pages.VerifyingHomePage;
import utils.AccessibilityUtils;
import utils.BaseTest;
import utils.NavigationUtils;
import utils.ReportUtils;

public class AccessibilityTest extends BaseTest {

    private VerifyingHomePage verifyingHomePage;
    private AccessibilityUtils accessibilityUtils;

    @BeforeTest
    public void setup() {
        verifyingHomePage = new VerifyingHomePage();
        accessibilityUtils = new AccessibilityUtils();
        ReportUtils.setUpExtentReport();
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
        // Navigate to the page
        NavigationUtils.navigateToTestingSite(pageName);
        verifyingHomePage.clickingCookieConsentBtn();

        // Create a test instance in the Extent Report
        ReportUtils.createTest("Accessibility Test for " + pageName);

        // Run accessibility checks
        accessibilityUtils.axeHome(ReportUtils.getTest(), pageName);
    }
}
