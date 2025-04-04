package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Base;
import utils.NavigationUtils;
import static utils.NavigationUtils.waitPageLoad;

public class CarPage extends Base {
    @FindBy(id = "priceTo") WebElement maxPrice;
    @FindBy(css = "[data-track-label=\"search-brand\"]") WebElement searchBrand;
    @FindBy(id = "thatsAllFolks") WebElement endOfPage;

    public CarPage(){
        PageFactory.initElements(Base.getDriver(),this);
    }

    public void filterPrice() {
        maxPrice.click();
        maxPrice.sendKeys("4 Lakh");
        maxPrice.sendKeys(Keys.ENTER);
        logger.info("Filtered by max price of 4 lakh");
    }

    public void searchForOnlyHonda(){
        JavascriptExecutor js = (JavascriptExecutor) Base.getDriver();
        js.executeScript("window.scrollBy(0,600);");

        searchBrand.sendKeys("Honda");
        searchBrand.sendKeys(Keys.ARROW_DOWN);
        searchBrand.sendKeys(Keys.ENTER);

        logger.info("Filtered by car brand Honda");

    }

    public void filterCars() {
        filterPrice();
        waitPageLoad();
        searchForOnlyHonda();
        waitPageLoad();
    }

}
