package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.Base;
import utils.ReportUtils;
import utils.TestListener;

@Listeners(TestListener.class)
public class HomePageTests {
    private HomePage homePage;

    @BeforeTest
    public void setup() {
        Base.getDriver();
        homePage = new HomePage();
        ReportUtils.setUpExtentReport();
    }

    @Test
    public void homepageTest() {
        ReportUtils.createTest("Homepage Test");
        homePage.clickingCookieConsentBtn();
        Assert.assertTrue(homePage.verifyOnHomePage(), "Failed to verify homepage navigation.");
        ReportUtils.getTest().pass("Verified Homepage navigation");
    }

    @AfterTest
    public void tearDown() {
        Base.quitDriver();
    }
}

