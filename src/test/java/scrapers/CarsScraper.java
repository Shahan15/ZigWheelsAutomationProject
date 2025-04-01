package scrapers;


import model.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import utils.Base;
import java.util.ArrayList;
import java.util.List;
import static utils.Base.driver;

public class CarsScraper {

    public static List<Car> webScrapeCars () {


        List<WebElement> carNameList = driver.findElements(By.cssSelector("[data-track-label=\"Car-name\"]"));
        List<WebElement> carPriceList = driver.findElements(By.cssSelector(".zw-cmn-price.n.pull-left.mt-3"));
        Base.logger.info("Pulling Car Name and Car Price");

        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < carNameList.size(); i++) {
            try {
                String carName = carNameList.get(i).getText();
                String carPrice = carPriceList.get(i).getText();

                cars.add(new Car(carPrice,carName));

            } catch (StaleElementReferenceException ex) {
                /*DOM changes sometimes thus giving you a
                StaleElement error. This is a fresh iteration and retries.*/
                Base.logger.warn("Encountered stale element. Retrying...");
                carNameList = driver.findElements(By.cssSelector("[data-track-label=\"Car-name\"]"));
                carPriceList = driver.findElements(By.cssSelector(".zw-cmn-price.n.pull-left.mt-3"));
                i--;
            }
        }
        return cars;

    }




}
