package org.example.tests;

import model.Bike;
import model.Car;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.NavigatingToBikes;
import pages.CarPage;
import pages.VerifyingHomePage;
import scrapers.BikeScraper;
import scrapers.CarsScraper;
import utils.ConvertPrice;
import utils.*;

import java.util.Arrays;
import java.util.List;

@Listeners(TestListener.class)
public class WebScrapingTests {
    private NavigatingToBikes navigatingToBikes;
    private VerifyingHomePage verifyingHomePage;
    private CarPage carPage;
    private BikeScraper bikeScraper;

    @BeforeTest
    public void setup() {
        Base.getDriver();
        verifyingHomePage = new VerifyingHomePage();
        navigatingToBikes = new NavigatingToBikes();
        ReportUtils.setUpExtentReport();
    }

    @Test
    public void WebScrapingBikes () {
        bikeScraper = new BikeScraper(); //this goes directly to filtered Honda bikes
        verifyingHomePage.clickingCookieConsentBtn();
        navigatingToBikes.clickViewMoreBikes();

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

    @Test
    public void webScrapingCars () {
        carPage = new CarPage();
        carPage.init();

        //Filter cars
        verifyingHomePage.clickingCookieConsentBtn();
        carPage.filterCars();

        //Scrape Bikes
        List<Car> cars = CarsScraper.webScrapeCars();

        //Defining headers for excel sheet
        List<String> headers = Arrays.asList("Car Names","Car Price");

        //Writing to Excel
        ExcelHandler.writeDataToExcel(
                cars,
                headers,
                car-> Arrays.asList(
                        car.getName(),
                        car.getPrice()
                ),
                "FilteredCarsData.xlsx"
        );
    }

    @AfterTest
    public void tearDown() {
        Base.quitDriver();
    }
}
