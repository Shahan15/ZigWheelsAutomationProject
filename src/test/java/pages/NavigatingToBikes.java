package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Base;
import utils.NavigationUtils;
import java.time.Duration;

public class NavigatingToBikes extends Base {
    @FindBy(css = "a[href=\"/newbikes\"]\n") WebElement newBikesLink;
    @FindBy(css = "[data-track-label=\"upcoming-tab\"]") WebElement upcomingSliderTab;
    @FindBy(id = "makeId") WebElement manufacturerDropdown;
    @FindBy(css = ".zw-cmn-loadMore") WebElement moreBikesBtn;

    VerifyingHomePage verifyingHomePage = new VerifyingHomePage();

    public NavigatingToBikes() {
        PageFactory.initElements(Base.getDriver(), this);
    }

    public void navigateToHomePage() {
        logger.info("Pagefactory for Navigating to bikes page initialised");
        NavigationUtils.navigateToTestingSite("HomePage");
        verifyingHomePage.clickingCookieConsentBtn();
    }

    public void navigateToNewBikes() {
        ((JavascriptExecutor) Base.getDriver()).executeScript("window.focus();");

        newBikesLink.click();
        logger.info("New bikes link pressed");

    }

    public void navigateToUpcomingSliderTab() {
        upcomingSliderTab.click();
        logger.info("Navigated to Upcoming bikes tab");
    }

    public void clickAllUpcomingBikesLink() {
        try{
            JavascriptExecutor js = (JavascriptExecutor) Base.getDriver();
            js.executeScript("window.scrollBy(0,300);");
            logger.info("scrolled down by 300 pixels");

            ((JavascriptExecutor) Base.getDriver()).executeScript("window.focus();");

            Base.getDriver().navigate().to(NavigationUtils.getTestingSiteUrl("upcomingBikesPage"));
            logger.info("All upcoming bikes link pressed");
        } catch (Exception e) {
            logger.error("Failed to click upcoming bikes link, {}", e.getMessage());
        }

    }

    public void filterBikes() {
        try {
            WebDriverWait wait = new WebDriverWait(Base.getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(manufacturerDropdown));

            manufacturerDropdown.click();
            manufacturerDropdown.sendKeys("Honda");
            manufacturerDropdown.sendKeys(Keys.ENTER);

            logger.info("Filtered by Manufacturer: Honda");
        } catch (Exception e) {
            logger.error("Failed to filter by Manufacturer: Honda" + e);
            throw new RuntimeException("Filtering by Manufacturer failed.", e);
        }
    }


    public void clickViewMoreBikes() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) Base.getDriver();

            //centers element
            js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center', inline: 'nearest'});", moreBikesBtn);

            // Wait until the element becomes visible
            NavigationUtils.webDriverWait(Base.getDriver(),15,moreBikesBtn);

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


    public void validatingOnlyHondaBikes(){
        String expectedHondaUrl = NavigationUtils.getTestingSiteUrl("filteredBikes");
        String actualUrl = Base.getDriver().getCurrentUrl();
        try {
            new WebDriverWait(Base.getDriver(), Duration.ofSeconds(20)).until(ExpectedConditions.urlToBe(expectedHondaUrl));
        } catch (TimeoutException e) {
            Base.logger.error("Timeout while waiting for URL to match expected " +
                    "Honda bikes page URL. Expected: {}, Actual: {}",
                    expectedHondaUrl, Base.getDriver().getCurrentUrl());
            throw e;
        }
        Assert.assertEquals(actualUrl, expectedHondaUrl, "URL after filtering does not match expected Honda bikes page URL!");
        Base.logger.info("Validating URL after filtering bikes. Expected: {}, Actual: {}", expectedHondaUrl, actualUrl);

    }

    public void navigatingToBikesPage() {
        navigateToNewBikes();
        navigateToUpcomingSliderTab();
        clickAllUpcomingBikesLink();
        NavigationUtils.waitPageLoad();
        filterBikes();
        NavigationUtils.waitPageLoad();
        clickViewMoreBikes();
        validatingOnlyHondaBikes();
    }

}
