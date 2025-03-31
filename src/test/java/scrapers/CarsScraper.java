package scrapers;

import org.openqa.selenium.WebDriver;
import utils.Base;
import utils.NavigationUtils;

public class CarsScraper {

    WebDriver driver = Base.driver;

    public CarsScraper() {
        driver.get(NavigationUtils.getTestingSiteUrl("CarsPage"));
    }

    public void filterPriceRange() {


    }




}
