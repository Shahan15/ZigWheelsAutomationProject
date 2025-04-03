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
import utils.ConvertPrice;
import utils.ExcelHandler;
import utils.FileHandler;
import utils.NavigationUtils;
import java.util.Arrays;
import java.util.List;

public class BikeScraperBdd {

    private NavigatingToBikes navigatingToBikes;
    private VerifyingHomePage verifyingHomePage;
    private BikeScraper bikeScraper;

    // Initialise page objects in the constructor
    public BikeScraperBdd() {
        verifyingHomePage = new VerifyingHomePage();
        navigatingToBikes = new NavigatingToBikes();
        bikeScraper = new BikeScraper();
    }

    @Given("the system navigates to the filtered bikes page")
    public void theUserIsOnFilteredBikePage() {
        NavigationUtils.navigateToTestingSite("filteredBikes");
        verifyingHomePage.clickingCookieConsentBtn();

    }

    @And("the system clicks view more bikes")
    public void theSystemClicksViewMoreBikes() {
        navigatingToBikes.clickViewMoreBikes();

    }

    @When("the system scrapes bike data")
    public void theSystemScrapesBikeData() {
        NavigatingToBikes navigatingToBikes = new NavigatingToBikes();
        navigatingToBikes.clickViewMoreBikes();
        List<Bike> bikes = BikeScraper.webScrapeBikes();

        List<String> headers = Arrays.asList("Bike Name", "Bike price", "Expected Launch Date");
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

    @Then("the data should be saved to an Excel Sheet")
    public void theDataShouldBeSavedToAnExcelSheet() {
        boolean fileExists = FileHandler.doesExcelSheetExist("FilteredBikesData.xlsx");
        Assert.assertTrue(fileExists, "Expected 'FilteredBikesData.xlsx' file does not exist");
    }
}
