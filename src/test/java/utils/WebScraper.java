package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebScraper {

    static WebDriver driver = Base.driver;

    public WebScraper() {
        driver.get(NavigationUtils.getTestingSiteUrl("filteredBikes"));
    }


    public static String convertPrice(String priceClean) {
        String cleaned = priceClean.replace("Rs.", "").trim();

        if (cleaned.contains("Lakh")) {
            cleaned = cleaned.replace("Lakh", "").trim();
            int converted = (int) (Double.parseDouble(cleaned) * 100000);//convert to rupees
            return String.valueOf(converted);
        } else if (cleaned.equalsIgnoreCase("Price to be announced")) {
            return "Price to be announced";
        } else {
            cleaned = cleaned.replace(",", "");
            int converted = Integer.parseInt(cleaned);
            return String.valueOf(converted);
        }
    }


    public static List<Bike> webScrapeBikes() {
        List<WebElement> bikeNamesElement = driver.findElements(By.cssSelector("a[data-track-label='model-name'] strong"));
        List<WebElement> bikePriceElement = driver.findElements(By.cssSelector("div.b.fnt-15"));
        List<WebElement> bikeLaunchDateElement = driver.findElements(By.cssSelector(".clr-try.fnt-14")); // Fetching launch dates
        Base.logger.info("Pulling Bike model names, prices, and expected launch dates from site");

        List<Bike> bikes = new ArrayList<>();

        for (int i = 0; i < bikeNamesElement.size(); i++) {
            String bikeName = bikeNamesElement.get(i).getText();
            String bikePrice = bikePriceElement.get(i).getText();
            String launchDate = bikeLaunchDateElement.get(i).getText();

            String convertedPrice = convertPrice(bikePrice);

            boolean includeBike; //filter to include bike
            if(convertedPrice.equalsIgnoreCase("Price to be announced")) {
                includeBike = true;
            } else {
                try {
                    int numericPrice = Integer.parseInt(convertedPrice);
                    includeBike = (numericPrice < 400000);
                } catch (NumberFormatException ex ) {
                    includeBike = false;
                }
            }
            if (includeBike) {
                bikes.add(new Bike(bikeName, bikePrice, launchDate));
                //if includeBike is true (either price to be announced or less than 4 lakh)
                //add it to our list
            }
        }
        return bikes;
    }
}

