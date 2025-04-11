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

public class Base {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    public static final Logger logger = LogManager.getLogger(Base.class);

    /**
     * @return The driver instance
     */
    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            logger.info("Initializing driver for Thread: " + Thread.currentThread().threadId());
            try {
                String browser = FileHandler.getConfigProperty("browser");
                WebDriver driver;
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
                driver.manage().window().maximize();
                driverThreadLocal.set(driver);
            } catch (Exception ex) {
                logger.error("Error initializing the browser: {}", ex.getMessage());
            }
        } else {
            logger.info("Reusing driver for Thread: " + Thread.currentThread().threadId());
        }
        return driverThreadLocal.get();
    }

    /**
     * Quits the driver instance
     */
    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }

}
