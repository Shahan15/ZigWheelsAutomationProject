package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Bike;
import org.testng.Assert;
import pages.NavigatingToBikes;
import pages.VerifyingHomePage;
import scrapers.BikeScraper;
import utils.*;
import java.util.Arrays;
import java.util.List;

public class BikeScraperBdd {

    private NavigatingToBikes navigatingToBikes;
    private VerifyingHomePage verifyingHomePage;

    // Initialise page objects in the constructor
    public BikeScraperBdd() {
        verifyingHomePage = new VerifyingHomePage();
        navigatingToBikes = new NavigatingToBikes();
        Base.logger.info("BikeScraperBdd initialized with VerifyingHomePage, NavigatingToBikes, and BikeScraper.");
    }

    @Given("the system navigates to the filtered bikes page")
    public void theUserIsOnFilteredBikePage() {
        Base.logger.info("Navigating to the filtered bikes page...");
        NavigationUtils.navigateToTestingSite("filteredBikes");
        Base.logger.info("Cookie consent button clicked.");
        verifyingHomePage.clickingCookieConsentBtn();
    }

    @When("the system scrapes bike data")
    public void theSystemScrapesBikeData() {
        Base.logger.info("Starting bike data scraping process...");
        NavigatingToBikes navigatingToBikes = new NavigatingToBikes();
        Base.logger.info("Clicking 'View More Bikes' button...");
        Base.logger.info("'View More Bikes' button clicked successfully. Proceeding with data scraping...");

        List<Bike> bikes = BikeScraper.webScrapeBikes();
        Base.logger.info("Bike data successfully scraped. Found {} bikes.", bikes.size());

        List<String> headers = Arrays.asList("Bike Name", "Bike price: Rs ", "Expected Launch Date");
        Base.logger.info("Writing bike data to Excel sheet with headers: {}.", headers);

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
        Base.logger.info("Bike data successfully written to 'FilteredBikesData.xlsx'.");
    }

    @Then("the data should be saved to an Excel Sheet")
    public void theDataShouldBeSavedToAnExcelSheet() {
        Base.logger.info("Verifying existence of 'FilteredBikesData.xlsx' file...");
        boolean fileExists = FileHandler.doesExcelSheetExist("FilteredBikesData.xlsx");
        Assert.assertTrue(fileExists, "Expected 'FilteredBikesData.xlsx' file does not exist");
        Base.logger.info("'FilteredBikesData.xlsx' file exists. Verification successful.");
    }
}
