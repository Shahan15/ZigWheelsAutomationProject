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
import static utils.NavigationUtils.waitPageLoad;

public class NavigatingToBikes extends Base {
    @FindBy(css = "a[href=\"/newbikes\"]\n") WebElement newBikesLink;
    @FindBy(css = "[data-track-label=\"upcoming-tab\"]") WebElement upcomingSliderTab;
    @FindBy(id = "makeId") WebElement manufacturerDropdown;
    @FindBy(xpath = "//*[@id=\"modelList\"]/li[16]/span") WebElement moreBikesBtn;

    VerifyingHomePage verifyingHomePage = new VerifyingHomePage();

    public NavigatingToBikes() {
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        logger.info("Pagefactory for Navigating to bikes page initialised");
        NavigationUtils.navigateToTestingSite("HomePage");
        verifyingHomePage.clickingCookieConsentBtn();
    }

    public void navigateToNewBikes() {
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

    public void filterBikes() {
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

            // Wait until the element becomes visible
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


    public void validatingOnlyHondaBikes(){
        String expectedHondaUrl = NavigationUtils.getTestingSiteUrl("filteredBikes");
        String actualUrl = Base.driver.getCurrentUrl();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.urlToBe(expectedHondaUrl));
        } catch (TimeoutException e) {
            Base.logger.error("Timeout while waiting for URL to match expected Honda bikes page URL. Expected: {}, Actual: {}", expectedHondaUrl, Base.driver.getCurrentUrl());
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
