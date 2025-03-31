package pages;

import io.cucumber.java.eo.Se;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Base;
import utils.NavigationUtils;

public class CarPage {
    @FindBy(id = "priceTo") WebElement maxPrice;
    @FindBy(css = "[data-track-label=\"search-brand\"]") WebElement searchBrand;

    WebDriver driver = Base.driver;
    HomePage homePage = new HomePage();

    public CarPage(){
        PageFactory.initElements(driver,this);
    }

    public void init () {
        driver.get(NavigationUtils.getTestingSiteUrl("CarsPage"));
    }

    public void filterPrice() {
        maxPrice.click();
        maxPrice.sendKeys("4 Lakh");
        maxPrice.sendKeys(Keys.ENTER);
    }

    public void searchForOnlyHonda(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600);");

        searchBrand.sendKeys("Honda");
        searchBrand.sendKeys(Keys.ARROW_DOWN);
        searchBrand.sendKeys(Keys.ENTER);
    }

    public void filterCars() {
        filterPrice();
        searchForOnlyHonda();
    }




}
