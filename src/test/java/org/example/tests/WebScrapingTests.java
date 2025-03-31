package org.example.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BikesPage;
import pages.HomePage;
import utils.Base;
import utils.TestListener;
import utils.WebScrapingUtils;
import utils.ReportUtils;

@Listeners(TestListener.class)
public class WebScrapingTests {
    private WebScrapingUtils webscrapingUtils;
    private BikesPage bikesPage;
    private HomePage homePage;

    @BeforeTest
    public void setup() {
        Base.getDriver();
        homePage = new HomePage();
        bikesPage = new BikesPage();
        webscrapingUtils = new WebScrapingUtils();
        ReportUtils.setUpExtentReport();
    }

    @Test
    public void WebScrapingBikes () {
        homePage.clickingCookieConsentBtn();
        bikesPage.clickViewMoreBikes();
        WebScrapingUtils.webScraper();
        WebScrapingUtils.writeToExcel();

    }

    @AfterTest
    public void tearDown() {
        Base.quitDriver();
    }
}
