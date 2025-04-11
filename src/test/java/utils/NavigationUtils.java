package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class NavigationUtils {

    /**
     * @param propertyKey Gets the URL of the page we want to test from Config.properties
     * @return the URL we want to test
     */
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

    /**
     * Actually navigates to the page we want to test
     * @param propertyKey the URL we want to navigate to
     */
    public static void navigateToTestingSite(String propertyKey) {
        try {
            // Retrieve the URL using existing getTestingSiteUrl method
            String url = getTestingSiteUrl(propertyKey);
            // Navigate to the URL
            Base.getDriver().navigate().to(url);

            ((JavascriptExecutor) Base.getDriver()).executeScript("window.focus();");

            Base.logger.info("Navigated to page with key: " + propertyKey + " and URL: " + url);
        } catch (RuntimeException ex) {
            Base.logger.error("Failed to navigate to page for property key: {}", propertyKey, ex);
            throw ex; // Re-throw to ensure the issue propagates
        }
    }


    /**
     * Waiting util
     * @param driver The driver instance
     * @param waitTime How long we want to wait for
     * @param element The element we are waiting to be actionable
     */
    public static void webDriverWait(WebDriver driver,int waitTime,WebElement element) {
        try{
            ((JavascriptExecutor) Base.getDriver()).executeScript("window.focus();");

            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(waitTime));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception ex){
            Base.logger.error("Element not visible after waiting for {} seconds",waitTime);
            throw new RuntimeException("Failed waiitng for element visibility");
        }
    }

    /**
     * Waiting for page to load util
     */
    public static void waitPageLoad() {
        WebDriver currentDriver = Base.getDriver();
        WebDriverWait wait = new WebDriverWait(currentDriver, Duration.ofSeconds(7));
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        Base.logger.info("page has fully loaded.");
    }
}
