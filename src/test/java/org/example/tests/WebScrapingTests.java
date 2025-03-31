package org.example.tests;

import model.Bike;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BikesPage;
import pages.HomePage;
import scrapers.BikeScraper;
import scrapers.ConvertPrice;
import utils.*;

import java.util.Arrays;
import java.util.List;

@Listeners(TestListener.class)
public class WebScrapingTests {
    private ConvertPrice convertPrice;
    private BikesPage bikesPage;
    private HomePage homePage;
    private BikeScraper bikeScraper;

    @BeforeTest
    public void setup() {
        Base.getDriver();
        homePage = new HomePage();
        bikesPage = new BikesPage();
        bikeScraper = new BikeScraper(); //this goes directly to filtered Honda bikes
        ReportUtils.setUpExtentReport();
    }

    @Test
    public void WebScrapingBikes () {
        homePage.clickingCookieConsentBtn();
        bikesPage.clickViewMoreBikes();

        //scrape bikes
        List<Bike> bikes = BikeScraper.webScrapeBikes();

        //defining headers
        List<String> headers = Arrays.asList("Bike Name","Bike price","Expected Launch Date");

        //calling the Excel handler with scraped data,headers
        ExcelHandler.writeDataToExcel(
                bikes,
                headers,
                bike -> Arrays.asList(
                        bike.getName(),
                        ConvertPrice.convertPrice(bike.getPrice()),
                        bike.getLaunchDate()
                ),
                "FilteredBikesData.xlsx"
        );
    }

//    @Test
//    public void webScrapingCars () {
//
//    }

    @AfterTest
    public void tearDown() {
        Base.quitDriver();
    }
}
