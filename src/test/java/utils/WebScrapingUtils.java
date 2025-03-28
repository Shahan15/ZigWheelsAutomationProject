package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HomePage;

import java.util.List;

public class WebScrapingUtils {

    static WebDriver driver = Base.driver;
    HomePage homePage = new HomePage();

    public WebScrapingUtils() {
        driver.get(NavigationUtils.getTestingSiteUrl("filteredBikes"));
    }


    public static void webScraper() {
        List<WebElement> bikeNames = driver.findElements(By.cssSelector("a[data-track-label='model-name'] strong"));

        for(int i =0;i < bikeNames.size();i++) {
            WebElement element = bikeNames.get(i);
            String bikeName = element.getText();
            System.out.println(bikeName);
        }

    }
}

