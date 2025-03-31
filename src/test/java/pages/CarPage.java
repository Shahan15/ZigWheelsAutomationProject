package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Base;
import utils.NavigationUtils;

public class CarPage {
    @FindBy(id = "priceTo") WebElement maxPrice;

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




}
