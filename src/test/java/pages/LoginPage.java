package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Base;
import utils.NavigationUtils;
import utils.ReportUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LoginPage extends Base {

    // Web elements defined using PageFactory
    @FindBy(id = "forum_login_title_lg") WebElement loginButton;
    @FindBy(css = "[data-track-label=\"Popup_Login/Register_with_Google\"]") WebElement googleLogin;
    @FindBy(id = "identifierId") WebElement emailInputField;
    @FindBy(id = "identifierNext") WebElement nextBtn;
    @FindBy(id = "next") WebElement tryAgain;

    VerifyingHomePage verifyingHomePage = new VerifyingHomePage();

    public LoginPage() {
        // Initialize WebElements using PageFactory
        PageFactory.initElements(Base.getDriver(), this);
    }

    /**
     * Navigate to the homepage and click the cookie consent button
     */
    public void navigateToHomePage() {
        NavigationUtils.navigateToTestingSite("HomePage");
        verifyingHomePage.clickingCookieConsentBtn();
    }

    /**
     * Click the Login button
     */
    public void clickLoginButton() {
        try {
            ((JavascriptExecutor) Base.getDriver()).executeScript("window.focus();");
            loginButton.click();
            logger.info("Clicked Login Button");
        } catch (Exception ex) {
            logger.error("Error clicking Login Button: {}", ex.getMessage());
            throw new RuntimeException("Login Button click failed.", ex);
        }
    }

    /**
     * Wait for the Google Login element to be clickable
     */
    public void waitForGoogleLogin() {
        try {
            WebDriverWait wait = new WebDriverWait(Base.getDriver(), Duration.ofSeconds(15));
            wait.until(ExpectedConditions.elementToBeClickable(googleLogin));
            logger.info("Google Login element is clickable");
        } catch (Exception ex) {
            logger.error("Error waiting for Google Login element: {}", ex.getMessage());
            throw new RuntimeException("Failed to wait for Google Login element.", ex);
        }
    }

    /**
     * Click the Google Login button
     */
    public void clickGoogleLoginButton() {
        try {
            ((JavascriptExecutor) Base.getDriver()).executeScript("window.focus();");
            googleLogin.click();
            logger.info("Clicked Google Login button");
        } catch (Exception ex) {
            logger.error("Error clicking Google Login button: {}", ex.getMessage());
            throw new RuntimeException("Google Login button click failed.", ex);
        }
    }

    /**
     * Switch to the popup window after clicking Google Login
     */
    public void switchToGoogleLoginPopup() {
        try {
            String parentWindow = Base.getDriver().getWindowHandle();
            Set<String> windowHandles = Base.getDriver().getWindowHandles();
            List<String> windowHandleList = new ArrayList<>(windowHandles);

            for (String winHandle : windowHandleList) {
                if (!winHandle.equals(parentWindow)) {
                    Base.getDriver().switchTo().window(winHandle);
                    logger.info("Switched to Google Login popup window");
                    break;
                }
            }
        } catch (Exception ex) {
            logger.error("Error switching to Google Login popup window: {}", ex.getMessage());
            throw new RuntimeException("Failed to switch to Google Login popup window.", ex);
        }
    }

    /**
     * Perform the modularized Google Login sequence
     */
    public void performGoogleLogin() {
        waitForGoogleLogin();
        clickGoogleLoginButton();
        switchToGoogleLoginPopup();
    }

    /**
     * Populate the email input field with a hardcoded email
     */
    public void setEmailInputField() {
        try {
            ((JavascriptExecutor) Base.getDriver()).executeScript("window.focus();");
            NavigationUtils.webDriverWait(Base.getDriver(), 15, emailInputField);
            emailInputField.sendKeys("example123@gmail.com"); // Hardcoded email
            logger.info("Email field populated with \"example123@gmail.com\" ");
        } catch (Exception ex) {
            logger.error("Error inputting email in input field: {}", ex.getMessage());
            throw new RuntimeException("Failed to input email.", ex);
        }
    }

    /**
     * Click the Next button after entering the email
     */
    public void clickNextBtn() {
        try {
            NavigationUtils.webDriverWait(Base.getDriver(), 15, nextBtn);
            nextBtn.click();
            logger.info("Clicked Next button after populating email field");
        } catch (Exception ex) {
            logger.error("Error clicking Next button: {}", ex.getMessage());
            throw new RuntimeException("Next button click failed.", ex);
        }
    }

    /**
     * Wait for the page to load and take a screenshot if necessary
     */
    public void waitForPageAndSS() {
        try {
            WebDriverWait wait = new WebDriverWait(Base.getDriver(), Duration.ofSeconds(15));
            wait.until(ExpectedConditions.elementToBeClickable(tryAgain));
            logger.info("Element with ID 'Try Again' is visible");

            Thread.sleep(2000); // Pause for visual inspection

            String screenshotPath = ReportUtils.takeScreenshot("LoginPage error");
            logger.info("Screenshot taken. File saved at: " + screenshotPath);
        } catch (Exception e) {
            logger.error("Error waiting for element with ID 'Try Again': {}", e.getMessage());
            throw new RuntimeException("Failed to wait for element or take screenshot.", e);
        }
    }

    /**
     * Perform the full login attempt sequence
     */
    public void attemptLogin() {
        navigateToHomePage();
        clickLoginButton();
        performGoogleLogin(); // Modularized method replaces previous implementation
        setEmailInputField();
        clickNextBtn();
        waitForPageAndSS();
    }
}
