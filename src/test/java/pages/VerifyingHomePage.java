package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Base;
import utils.NavigationUtils;
import java.time.Duration;


public class VerifyingHomePage extends Base {
    @FindBy(css = "img.banner.mobileimage") WebElement bannerImg;
    @FindBy(css = "p.fc-button-label") WebElement cookieConsentBtn;


    public VerifyingHomePage() {
        PageFactory.initElements(driver, this);
    }

    public void init() {
        logger.info("PageFactory initialised");
        driver.get(NavigationUtils.getTestingSiteUrl("HomePage"));
    }

    public void clickingCookieConsentBtn() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement cookieConsentBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("p.fc-button-label")));
            logger.info("Cookie Consent Button Attributes - isEnabled: {}, isDisplayed: {}, Text: {}",
                    cookieConsentBtn.isEnabled(), cookieConsentBtn.isDisplayed(), cookieConsentBtn.getText());

            cookieConsentBtn.click();
            logger.info("Accepted cookies");

        } catch (Exception ex) {
            logger.error("Error occurred while trying to click accept cookies button: {}", ex.getMessage());
            Assert.fail("Failed to click the cookie consent button due to: " + ex.getMessage());
        }
    }


    public boolean verifyOnHomePage() {
        try {
            NavigationUtils.webDriverWait(driver, 5, bannerImg);
            if (bannerImg.isDisplayed()) {
                logger.info("The Banner image is present, navigated to homepage successfully");
                return true;
            } else {
                logger.error("Banner image not displayed, homepage verification failed.");
                return false;
            }
        } catch (Exception ex) {
            logger.error("Banner image did not load, navigation to homepage unsuccessful: {}", ex.getMessage());
            return false;
        }
    }

//    public void homePageVerificationSteps () {
//        clickingCookieConsentBtn();
//        verifyOnHomePage();
//    }

}
