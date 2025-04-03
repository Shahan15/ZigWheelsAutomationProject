package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Base;
import utils.NavigationUtils;
import java.time.Duration;


public class VerifyingHomePage extends Base {
    @FindBy(xpath = "//*[@id=\"homeslider\"]/li[1]/a/img") WebElement bannerImg;

    public VerifyingHomePage() {
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        logger.info("PageFactory initialised");
        NavigationUtils.navigateToTestingSite("HomePage");
    }

    public void clickingCookieConsentBtn() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Wait for the element to be clickable, but do not fail if it doesn't appear
            WebElement cookieConsentBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("p.fc-button-label")));
            logger.info("Cookie Consent Button Attributes - isEnabled: {}, isDisplayed: {}, Text: {}",
                    cookieConsentBtn.isEnabled(), cookieConsentBtn.isDisplayed(), cookieConsentBtn.getText());

            cookieConsentBtn.click();
            logger.info("Accepted cookies");

        } catch (TimeoutException timeoutEx) {
            // Element did not appear; log and continue
            logger.warn("Cookie consent button did not appear within 5 seconds. Continuing without clicking.");
        } catch (Exception ex) {
            // Log any unexpected exceptions
            logger.error("Error occurred while trying to click accept cookies button: {}", ex.getMessage());
        }

        //alternate cookie button handling
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Wait for the element to be clickable, but do not fail if it doesn't appear
            WebElement cookieConsentBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("p.fc-button-text")));
            logger.info("Cookie Consent Button 2 Attributes - isEnabled: {}, isDisplayed: {}, Text: {}",
                    cookieConsentBtn.isEnabled(), cookieConsentBtn.isDisplayed(), cookieConsentBtn.getText());

            cookieConsentBtn.click();
            logger.info("Accepted cookies Button 2 ");

        } catch (TimeoutException timeoutEx) {
            // Element did not appear; log and continue
            logger.warn("Cookie consent button 2 did not appear within 5 seconds. Continuing without clicking.");
        } catch (Exception ex) {
            // Log any unexpected exceptions
            logger.error("Error occurred while trying to click accept cookies button 2: {}", ex.getMessage());
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


}
