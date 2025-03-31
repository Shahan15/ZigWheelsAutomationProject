package org.example.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BikesPage;
import pages.HomePage;
import utils.*;

import java.util.Arrays;
import java.util.List;

@Listeners(TestListener.class)
public class WebScrapingTests {
    private WebScraper webscrapingUtils;
    private BikesPage bikesPage;
    private HomePage homePage;

    @BeforeTest
    public void setup() {
        Base.getDriver();
        homePage = new HomePage();
        bikesPage = new BikesPage();
        webscrapingUtils = new WebScraper();
        ReportUtils.setUpExtentReport();
    }

    @Test
    public void WebScrapingBikes () {
        homePage.clickingCookieConsentBtn();
        bikesPage.clickViewMoreBikes();

        //scrape bikes
        List<Bike> bikes = WebScraper.webScrapeBikes();

        //defining headers
        List<String> headers = Arrays.asList("Bike Name","Bike price","Expected Launch Date");

        //calling the Excel handler with scraped data,headers
        ExcelHandler.writeDataToExcel(
                bikes,
                headers,
                bike -> Arrays.asList(
                        bike.getName(),
                        WebScraper.convertPrice(bike.getPrice()),
                        bike.getLaunchDate()
                ),
                "FilteredBikesData.xlsx"
        );
    }

    @AfterTest
    public void tearDown() {
        Base.quitDriver();
    }
}
