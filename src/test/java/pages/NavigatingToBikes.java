package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Base;
import utils.NavigationUtils;
import java.time.Duration;
import static utils.NavigationUtils.waitPageLoad;

public class NavigatingToBikes extends Base {
    @FindBy(css = "[data-track-label=\"nav-newbikes\"]") WebElement newBikesLink;
    @FindBy(css = "[data-track-label=\"upcoming-tab\"]") WebElement upcomingSliderTab;
    @FindBy(id = "makeId") WebElement manufacturerDropdown;
    @FindBy(xpath = "//*[@id=\"modelList\"]/li[16]/span") WebElement moreBikesBtn;

    WebDriver driver = Base.driver;
    VerifyingHomePage verifyingHomePage = new VerifyingHomePage();

    public NavigatingToBikes() {
        PageFactory.initElements(driver, this);
    }

    public void init() {
        logger.info("Pagefactory for Navigating to bikes page initialised");
        driver.get(NavigationUtils.getTestingSiteUrl("HomePage"));
    }

    public void navigateToNewBikes() {
        verifyingHomePage.clickingCookieConsentBtn();
        newBikesLink.click();
        logger.info("New bikes link pressed");

    }

    public void navigateToUpcomingSliderTab() {
        upcomingSliderTab.click();
        logger.info("Navigated to Upcoming bikes tab");
    }

    public void clickAllUpcomingBikesLink() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300);");
        logger.info("scrolled down by 800 pixels");
        driver.navigate().to(NavigationUtils.getTestingSiteUrl("upcomingBikesPage"));
        logger.info("All upcoming bikes link pressed");
    }

    private void filterBikes() {
        manufacturerDropdown.click();
        manufacturerDropdown.sendKeys("Honda");
        manufacturerDropdown.sendKeys(Keys.ENTER);
        logger.info("Filtered by Manufacturer: Honda");
    }

    public void clickViewMoreBikes() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            //centers element
            js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center', inline: 'nearest'});", moreBikesBtn);

            // Wait until the element becomes clickable
            NavigationUtils.webDriverWait(driver,10,moreBikesBtn);

            // Attempt a regular click
            try {
                moreBikesBtn.click();
                logger.info("Clicked view more bikes");
            } catch (ElementClickInterceptedException e) {
                // Fall back to a JS click if a normal click is intercepted
                logger.warn("Standard click intercepted. Falling back to JavaScript click.", e);
                js.executeScript("arguments[0].click();", moreBikesBtn);
            }
        } catch (Exception ex) {
            logger.error("Error clicking 'View More Bikes' button: {}", ex.getMessage());
        }
    }

    public void navigatingToBikesPage() {
        navigateToNewBikes();
        navigateToUpcomingSliderTab();
        clickAllUpcomingBikesLink();
        waitPageLoad();
        filterBikes();
        clickViewMoreBikes();
    }

}
