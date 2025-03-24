package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {

    public static final Logger logger = LogManager.getLogger(Base.class);
    public static WebDriver driver;

    public static WebDriver getDriver() {
        try {
            String browser = FileHandler.getConfigProperty("browser");
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    logger.info("Chrome has been selected");
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    logger.info("Microsoft Edge has been selected");
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    logger.info("Firefox has been selected");
                    break;
                default:
                    driver = new ChromeDriver();
                    logger.info("Default browser has been selected - Chrome");
                    break;
            }
        } catch (Exception ex) {
            logger.error("There was an error initializing {}", ex.getMessage());
        }

        driver.manage().window().maximize();

        //Calls method to get url we want to test
        getTestingSiteUrl();

        return driver;
    }


    public static void getTestingSiteUrl() {
        try {
            String url = FileHandler.getProperty("TestingSite");
            driver.get(url);
            logger.info("Opening {}",url);
        }catch (Exception ex){
            logger.error("There was an error opening the url {}", ex.getMessage());

        }
    }

    public static void quitDriver() {
        if (driver !=null){
            driver.quit();
            driver = null;
        }
    }


}
