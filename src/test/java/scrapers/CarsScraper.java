package scrapers;


import model.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import utils.Base;
import java.util.ArrayList;
import java.util.List;

public class CarsScraper {

    /**
     *
     * @return the scraped cars price and name
     */
    public static List<Car> webScrapeCars () {
        List<WebElement> carNameList = Base.getDriver().findElements(By.cssSelector("[data-track-label=\"Car-name\"]"));
        List<WebElement> carPriceList = Base.getDriver().findElements(By.cssSelector(".zw-cmn-price.n.pull-left.mt-3"));
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
                carNameList = Base.getDriver().findElements(By.cssSelector("[data-track-label=\"Car-name\"]"));
                carPriceList = Base.getDriver().findElements(By.cssSelector(".zw-cmn-price.n.pull-left.mt-3"));
                i--;
            }
        }
        return cars;

    }




}
