package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Car;
import org.testng.Assert;
import pages.CarPage;
import pages.NavigatingToBikes;
import pages.VerifyingHomePage;
import scrapers.CarsScraper;
import utils.ExcelHandler;
import utils.FileHandler;
import utils.NavigationUtils;

import java.util.Arrays;
import java.util.List;

import static utils.NavigationUtils.waitPageLoad;

public class CarScraperBdd {

    private VerifyingHomePage verifyingHomePage;
    private CarPage carPage;


    public CarScraperBdd() {
        verifyingHomePage = new VerifyingHomePage();
        carPage = new CarPage();
    }


    @Given("the system navigates to the used cars page")
    public void theSystemNavigatesToTheUsedCarsPage() {
        NavigationUtils.navigateToTestingSite("CarsPage");
        verifyingHomePage.clickingCookieConsentBtn();
    }

    @And("the system filters by max price")
    public void theSystemFiltersByMaxPrice() {
        carPage.filterPrice();
        waitPageLoad();

    }

    @And("the system filters by only Honda cars")
    public void theSystemFiltersByOnlyHondaCars() {
        carPage.searchForOnlyHonda();
        waitPageLoad();
    }

    @When("the system scrapes car data")
    public void theSystemScrapesCarData() {
        List<Car> cars = CarsScraper.webScrapeCars();

        //Defining headers for excel sheet
        List<String> headers = Arrays.asList("Car Names", "Car Price");

        //Writing to Excel
        ExcelHandler.writeDataToExcel(
                cars,
                headers,
                car -> Arrays.asList(
                        car.getName(),
                        car.getPrice()
                ),
                "FilteredCarsData.xlsx"
        );

    }

    @Then("the data should be saved to a filtered cars Excel Sheet")
    public void theDataShouldBeSavedToAFilteredCarsExcelSheet() {
        boolean fileExists = FileHandler.doesExcelSheetExist("FilteredCarsData.xlsx");
        Assert.assertTrue(fileExists, "Expected 'FilteredCarsData.xlsx' file does not exist");
    }
}
