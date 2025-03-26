package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Base;

import java.time.Duration;


public class HomePage extends Base {
    @FindBy(css = "img.banner.mobileimage")
    WebElement bannerImg;
    @FindBy(css = "p.fc-button-label")
    WebElement cookieConsentBtn;


    public HomePage() {
        PageFactory.initElements(driver, this);
        logger.info("PageFactory initialised");
    }

    public void clickingCookieConsentBtn() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(cookieConsentBtn));
            logger.info("Waiting 5 seconds for accept cookies button to display");

            if (cookieConsentBtn.isDisplayed()) {
                cookieConsentBtn.click();
                logger.info("accepted cookies");
            }

        } catch (Exception ex) {
            logger.error("Error occurred while trying to click accept cookies button: {}", ex.getMessage());
            Assert.fail("Failed to click the cookie consent button due to: " + ex.getMessage());
        }
    }


    public boolean verifyOnHomePage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(bannerImg));
            logger.info("Waiting 5 seconds for banner image to load");

            logger.info("The Banner image is present, navigated to home page successfully");
            return bannerImg.isDisplayed();

        } catch (Exception ex) {
            logger.error("Banner image did not load, navigation to homepage unsuccessful: {}", ex.getMessage());
            return false;
        }

    }

}
