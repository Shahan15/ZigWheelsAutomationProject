package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.VerifyingHomePage;
import utils.Base;
import utils.BaseTest;
import utils.ReportUtils;
import utils.TestListener;
import static utils.ReportUtils.extent;

@Listeners(TestListener.class)
public class VerifyingHomePageTests extends BaseTest {
    private VerifyingHomePage verifyingHomePage;

    @BeforeTest
    public void setup() {
        ReportUtils.setUpExtentReport(this.getClass().getSimpleName());
        TestListener.setExtent(extent);

        verifyingHomePage = new VerifyingHomePage();
        verifyingHomePage.navigateToHomePage();
    }

    @Test
    public void homepageTest() {
        // Create a report for the test
        ReportUtils.createTest("Homepage Test");

        // Click the cookie consent button
        verifyingHomePage.clickingCookieConsentBtn();

        // Verify navigation to the homepage
        Assert.assertTrue(verifyingHomePage.verifyOnHomePage(), "Failed to verify homepage navigation.");

        // Log success in the report
        ReportUtils.getTest().pass("Verified Homepage navigation successfully");
        Base.logger.info("Homepage verification completed successfully");
    }

}

