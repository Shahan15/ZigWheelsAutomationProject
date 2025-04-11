package org.example.tests;

import model.Bike;
import model.Car;
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

import static utils.ReportUtils.extent;

@Listeners(TestListener.class)
public class WebScrapingTests extends BaseTest {
    public NavigatingToBikes navigatingToBikes;
    public VerifyingHomePage verifyingHomePage;
    public CarPage carPage;

    @BeforeTest
    public void setup() {
        ReportUtils.setUpExtentReport(this.getClass().getSimpleName());
        TestListener.setExtent(extent);

        verifyingHomePage = new VerifyingHomePage();
        navigatingToBikes = new NavigatingToBikes();
    }

    @Test
    public void WebScrapingBikes () {
        NavigationUtils.navigateToTestingSite("filteredBikes");
        verifyingHomePage.clickingCookieConsentBtn();

        //scrape bikes
        List<Bike> bikes = BikeScraper.webScrapeBikes();

        //defining headers
        List<String> headers = Arrays.asList("Bike Name","Bike price: Rs ","Expected Launch Date");

        //calling the Excel handler with scraped data,headers
        ExcelHandler.writeDataToExcel(
                bikes,
                headers,
                //This is the row mapper
                bike -> Arrays.asList(
                        bike.getName(),
                        ConvertPrice.convertPrice(bike.getPrice()),
                        bike.getLaunchDate()
                ),
                "FilteredBikesData.xlsx"
        );
    }

    @Test
    public void WebScrapingCars() {
        NavigationUtils.navigateToTestingSite("CarsPage");
        verifyingHomePage.clickingCookieConsentBtn();

        //Filter cars
        carPage = new CarPage();
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

}
