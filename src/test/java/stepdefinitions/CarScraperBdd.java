package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Car;
import org.testng.Assert;
import pages.CarPage;
import pages.VerifyingHomePage;
import scrapers.CarsScraper;
import utils.Base;
import utils.ExcelHandler;
import utils.FileHandler;
import utils.NavigationUtils;
import java.util.Arrays;
import java.util.List;

import static utils.NavigationUtils.waitPageLoad;

public class CarScraperBdd {

    private final VerifyingHomePage verifyingHomePage;
    private final CarPage carPage;

    public CarScraperBdd() {
        verifyingHomePage = new VerifyingHomePage();
        carPage = new CarPage();
        Base.logger.info("CarScraperBdd initialized with VerifyingHomePage and CarPage objects.");
    }

    @Given("the system navigates to the used cars page")
    public void theSystemNavigatesToTheUsedCarsPage() {
        Base.logger.info("Navigating to the used cars page...");
        NavigationUtils.navigateToTestingSite("CarsPage");
        Base.logger.info("Cookie consent button clicked.");
        verifyingHomePage.clickingCookieConsentBtn();
    }

    @And("the system filters by max price")
    public void theSystemFiltersByMaxPrice() {
        Base.logger.info("Applying filter by maximum price...");
        carPage.filterPrice();
        Base.logger.info("Filter by maximum price applied successfully. Waiting for page to load...");
        waitPageLoad();
    }

    @And("the system filters by only Honda cars")
    public void theSystemFiltersByOnlyHondaCars() {
        Base.logger.info("Applying filter for only Honda cars...");
        carPage.searchForOnlyHonda();
        Base.logger.info("Filter for Honda cars applied successfully. Waiting for page to load...");
        waitPageLoad();
    }

    @When("the system scrapes car data")
    public void theSystemScrapesCarData() {
        Base.logger.info("Starting car data scraping process...");
        List<Car> cars = CarsScraper.webScrapeCars();
        Base.logger.info("Car data successfully scraped. Found {} cars.", cars.size());

        // Defining headers for excel sheet
        List<String> headers = Arrays.asList("Car Names", "Car Price");
        Base.logger.info("Writing car data to Excel sheet with headers: {}.", headers);

        // Writing to Excel
        ExcelHandler.writeDataToExcel(
                cars,
                headers,
                car -> Arrays.asList(
                        car.getName(),
                        car.getPrice()
                ),
                "FilteredCarsData.xlsx"
        );
        Base.logger.info("Car data successfully written to 'FilteredCarsData.xlsx'.");
    }

    @Then("the data should be saved to a filtered cars Excel Sheet")
    public void theDataShouldBeSavedToAFilteredCarsExcelSheet() {
        Base.logger.info("Verifying existence of 'FilteredCarsData.xlsx' file...");
        boolean fileExists = FileHandler.doesExcelSheetExist("FilteredCarsData.xlsx");
        Assert.assertTrue(fileExists, "Expected 'FilteredCarsData.xlsx' file does not exist");
        Base.logger.info("'FilteredCarsData.xlsx' file exists. Verification successful.");
    }
}
