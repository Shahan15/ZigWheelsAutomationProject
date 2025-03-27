package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigationUtils {
    public static String getTestingSiteUrl(String propertyKey) {
        try {
            String url = FileHandler.getProperty(propertyKey);
            if (url == null || url.isEmpty()) {
                Base.logger.error("URL is empty for property key: {}", propertyKey);
                throw new RuntimeException("URL is not set for property key: " + propertyKey);
            }
            return url;
        } catch (Exception ex) {
            Base.logger.error("There was an error retrieving the URL: {}", ex.getMessage());
            throw new RuntimeException("Failed to retrieve URL due to: " + ex.getMessage(), ex);
        }
    }


    public static void webDriverWait(WebDriver driver,int waitTime,WebElement element) {
        try{
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(waitTime));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception ex){
            Base.logger.error("Element not visible after waiting for {} seconds",waitTime);
            throw new RuntimeException("Failed waiitng for element visibility");
        }
    }

    public static void pause(int milliseconds) {
        try{
            Thread.sleep(milliseconds);
        }catch (InterruptedException ex) {
            Base.logger.error("Pause interrupted: {}", ex.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }

}
