package org.example.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.BikesPage;
import pages.HomePage;
import utils.Base;
import utils.NavigationUtils;
import utils.ReportUtils;

public class TestBoard {
    private HomePage homePage;
    private BikesPage bikesPage;

    @BeforeTest
    public void setup() {
        Base.getDriver();
        NavigationUtils.getTestingSiteUrl("HomePage");
        homePage = new HomePage();
        bikesPage = new BikesPage();
        ReportUtils.setUpExtentReport();
    }

    @Test
    public void homepageTest () {
        ReportUtils.createTest("Homepage Test");

        homePage.clickingCookieConsentBtn();
        Assert.assertTrue(homePage.verifyOnHomePage());
        ReportUtils.test.pass("Verified Homepage navigation");

    }

    @Test
    public void NavigatingToBikesPageTest () {
        ReportUtils.createTest("Navigating to Bikes page Test");
        homePage.clickingCookieConsentBtn();
        bikesPage.navigatingToBikesPage();
        ReportUtils.test.pass("Navigated to Bikes page Successfully");

    }

    @AfterTest
    public void tearDown() {
        Base.quitDriver();
        ReportUtils.flushReport();
    }


}
