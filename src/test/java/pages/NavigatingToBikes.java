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
    @FindBy(css = "[title='upcoming Honda bikes']") WebElement filterByHonda;
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
            JavascriptExecutor js = (JavascriptExecutor) Base.getDriver();

            // Scroll until the filterByHonda element is visible in the viewport
            WebElement filterByHonda = Base.getDriver().findElement(By.cssSelector("[title='upcoming Honda bikes']"));
            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", filterByHonda);

            // Wait for the filterByHonda element to be clickable
            WebDriverWait wait = new WebDriverWait(Base.getDriver(), Duration.ofSeconds(15));
            wait.until(ExpectedConditions.elementToBeClickable(filterByHonda));

            Thread.sleep(500);

            // Click on the filterByHonda element
            filterByHonda.click();
            logger.info("Filtered by Manufacturer: Honda");
        } catch (Exception e) {
            logger.error("Failed to filter by Manufacturer: Honda. Error: {}", e.getMessage());
            throw new RuntimeException("Filtering by Manufacturer failed.", e);
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
        validatingOnlyHondaBikes();
    }

}
