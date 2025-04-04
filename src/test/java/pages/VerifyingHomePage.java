package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Base;
import utils.NavigationUtils;
import java.time.Duration;


public class VerifyingHomePage extends Base {
    @FindBy(xpath = "//*[@id=\"homeslider\"]/li[1]/a/img") WebElement bannerImg;

    public VerifyingHomePage() {
        PageFactory.initElements(Base.getDriver(), this);
    }

    public void navigateToHomePage() {
        logger.info("PageFactory initialised");
        NavigationUtils.navigateToTestingSite("HomePage");
    }

    public void clickingCookieConsentBtn() {
        WebDriver driver = Base.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Define a custom ExpectedCondition that returns a clickable element for either locator.
        ExpectedCondition<WebElement> consentButtonClickable = new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                try {
                    WebElement btn1 = driver.findElement(By.cssSelector("p.fc-button-label"));
                    if (btn1.isDisplayed() && btn1.isEnabled()) {
                        return btn1;
                    }
                } catch (Exception ignore) {
                    // If not found or not clickable, continue to next.
                }
                try {
                    WebElement btn2 = driver.findElement(By.cssSelector("p.fc-button-text"));
                    if (btn2.isDisplayed() && btn2.isEnabled()) {
                        return btn2;
                    }
                } catch (Exception ignore) {
                    // If not found or not clickable, keep waiting.
                }
                return null;
            }

            @Override
            public String toString() {
                return "a clickable cookie consent button with either p.fc-button-label or p.fc-button-text";
            }
        };

        try {
            // Wait until the custom condition returns a non-null WebElement
            WebElement cookieConsentBtn = wait.until(consentButtonClickable);
            logger.info("Cookie Consent Button Attributes - isEnabled: {}, isDisplayed: {}, Text: {}",
                    cookieConsentBtn.isEnabled(), cookieConsentBtn.isDisplayed(), cookieConsentBtn.getText());
            cookieConsentBtn.click();
            logger.info("Accepted cookies");
        } catch (TimeoutException te) {
            logger.warn("Cookie consent button did not appear within 5 seconds. Continuing without clicking.");
        } catch (Exception ex) {
            logger.error("Error occurred while trying to click cookie consent button: {}", ex.getMessage());
        }
    }

    public boolean verifyOnHomePage() {
        try {
            NavigationUtils.webDriverWait(Base.getDriver(), 5, bannerImg);
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
