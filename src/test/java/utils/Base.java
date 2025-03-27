package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static utils.NavigationUtils.getTestingSiteUrl;

public class Base {

    public static final Logger logger = LogManager.getLogger(Base.class);
    public static WebDriver driver;

    public static WebDriver getDriver() {
        try {
            String browser = FileHandler.getConfigProperty("browser");
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--disable-notifications");
                    driver = new ChromeDriver(chromeOptions);
                    logger.info("Chrome has been selected");
                    break;
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--disable-notifications");
                    driver = new EdgeDriver(edgeOptions);
                    logger.info("Microsoft Edge has been selected");
                    break;
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addPreference("dom.webnotifications.enabled", false);
                    driver = new FirefoxDriver(firefoxOptions);
                    logger.info("Firefox has been selected");
                    break;
                default:
                    ChromeOptions defaultOptions = new ChromeOptions();
                    defaultOptions.addArguments("--disable-notifications");
                    driver = new ChromeDriver(defaultOptions);
                    logger.info("Default browser has been selected - Chrome");
                    break;
            }
        } catch (Exception ex) {
            logger.error("There was an error initializing {}", ex.getMessage(), ex);
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
