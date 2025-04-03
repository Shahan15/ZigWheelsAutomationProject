package pages;

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

import static utils.NavigationUtils.waitPageLoad;

public class LoginPage extends Base {
    @FindBy(id = "forum_login_title_lg") WebElement loginButton;
    @FindBy(css = "[data-track-label=\"Popup_Login/Register_with_Google\"]") WebElement googleLogin;
    @FindBy(id = "identifierId") WebElement emailInputField;
    @FindBy(id = "identifierNext") WebElement nextBtn;
    @FindBy(id = "next") WebElement tryAgain;

    VerifyingHomePage verifyingHomePage = new VerifyingHomePage();

    public LoginPage() {
        PageFactory.initElements(driver,this);
    }

    public void navigateToHomePage () {
        NavigationUtils.navigateToTestingSite("HomePage");
        verifyingHomePage.clickingCookieConsentBtn();
    }

    public void clickLoginButton () {
        loginButton.click();
        logger.info("clicked LoginButton");
    }

    public void clickGoogleLogin() {
        try {
            NavigationUtils.webDriverWait(driver,5,googleLogin);
            googleLogin.click();
            logger.info("clicked Google login");

            String parentWindow = driver.getWindowHandle();
            Set<String> windowHandles = driver.getWindowHandles(); //getWindowHandles returns a SET like output.
            //so appropriate to use SET.
            List<String> windowHandleList = new ArrayList<>(windowHandles); // Convert Set to List. cant index set.

            // Access each handle by index
            for (String winHandle : windowHandleList) {
                if(!winHandle.equals(parentWindow)){
                    try {
                        driver.switchTo().window(winHandle);
                        logger.info("Switched to login pop up window");
                        break;
                    }catch (Exception ex){
                        logger.error("Error switching to login pop up window {}",ex.getMessage());
                    }
                }
            }

        } catch (Exception ex){
            logger.error("Error clicking google login button {}",ex.getMessage());
        }
    }

    public void setEmailInputField() {
        try{
            NavigationUtils.webDriverWait(driver,5,emailInputField);
            emailInputField.sendKeys("example123@gmail.com");
            logger.info("Email field populated with \"example123@gmail.com\" ");
        }catch (Exception ex){
            logger.error("Error inputting example email in input field {}",ex.getMessage());
        }
    }
    public void clickNextBtn() {
        try {
            nextBtn.click();
            logger.info("Clicked next button after populating email field");
        }catch (Exception ex) {
            logger.error("error clicking next button {}",ex.getMessage());
        }
    }

    public void waitForPageAndSS(){
        try {
            // Wait up to 10 seconds for the element to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(tryAgain));
            logger.info("Element with ID 'Try Again' is visible");

            Thread.sleep(2000);

            // Take a screenshot once the element is visible
            String screenshotPath = ReportUtils.takeScreenshot("LoginPage error");
            logger.info("Screenshot taken. File saved at: " + screenshotPath);
        } catch (Exception e) {
            logger.error("Error waiting for element with ID 'Try Again': {}", e.getMessage());
        }
    }


    public void attemptLogin() {
        clickLoginButton();
        clickGoogleLogin();
        setEmailInputField();
        clickNextBtn();
        waitForPageAndSS();
    }


}
