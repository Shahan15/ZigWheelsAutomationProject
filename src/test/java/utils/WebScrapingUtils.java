package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebScrapingUtils {

    static WebDriver driver = Base.driver;

    public WebScrapingUtils() {
        driver.get(NavigationUtils.getTestingSiteUrl("filteredBikes"));
    }


    public static String  convertPrice(String priceClean) {
        String cleaned = priceClean.replace("Rs.", "").trim();

        if(cleaned.contains("Lakh")){
            cleaned = cleaned.replace("Lakh","").trim();
            int converted = (int) (Double.parseDouble(cleaned) * 100000);//convert to rupees
            return String.valueOf(converted);
        } else if (cleaned.equalsIgnoreCase("Price to be announced")) {
            return "Price to be announced";
        } else {
            cleaned = cleaned.replace(",","");
            int converted = Integer.parseInt(cleaned);
            return String.valueOf(converted);
        }
    }


    public static void webScraper() {
        List<WebElement> bikeNamesElement = driver.findElements(By.cssSelector("a[data-track-label='model-name'] strong"));
        List<WebElement> bikePriceElement = driver.findElements(By.cssSelector("div.b.fnt-15"));
        List<WebElement> bikeLaunchDateElement = driver.findElements(By.cssSelector(".clr-try.fnt-14")); // Fetching launch dates
        Base.logger.info("Pulling Bike model names, prices, and expected launch dates from site");

        for (int i = 0; i < bikeNamesElement.size(); i++) {
            String bikeName = bikeNamesElement.get(i).getText();
            String bikePrice = bikePriceElement.get(i).getText();
            String launchDate = bikeLaunchDateElement.get(i).getText(); // Get the launch date
            String convertedPrice = convertPrice(bikePrice);

            if (convertedPrice.equalsIgnoreCase("Price to be announced")) {
                // Display bikes with no price
                System.out.println("Bike Name: " + bikeName + "     Bike Price: Price to be announced     Expected Launch: " + launchDate);            } else {
                int numericPrice = Integer.parseInt(convertedPrice); // Proper parsing of numeric price
                if (numericPrice < 400000) { // Only display bikes with price below 4 lakh
                    System.out.println("Bike Name: " + bikeName + "     Bike Price: Rs. " + convertedPrice + "    " + launchDate);                }
            }
        }
    }
}

