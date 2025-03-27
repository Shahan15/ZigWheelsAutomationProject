package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Base;
import utils.NavigationUtils;

import java.time.Duration;

public class BikesPage extends Base {
    @FindBy(css = "[data-track-label=\"nav-newbikes\"]") WebElement newBikesLink;
    @FindBy(css = "[data-track-label=\"upcoming-tab\"]") WebElement upcomingSliderTab;
    @FindBy(css = "a[href=\"/upcoming-bikes\"]") WebElement allUpcomingBikesLink;
    @FindBy(id = "makeId") WebElement manufacturerDropdown;

    WebDriver driver = Base.driver;

    public BikesPage() {
        PageFactory.initElements(driver, this);
        logger.info("Pagefactory for Navigating to bikes page initialised");
    }

    public void navigateToNewBikes() {
        newBikesLink.click();
        logger.info("New bikes link pressed");
//        NavigationUtils.pause(20);
//        logger.info("Waiting for 2 seconds for animations to load properly");

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

    public void waitPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        logger.info("Upcoming Bikes page has fully loaded.");
    }

    private void filterBikes() {
        manufacturerDropdown.click();
        manufacturerDropdown.sendKeys("Honda");
        manufacturerDropdown.sendKeys(Keys.ENTER);
        logger.info("Filtered by Manufacturer: Honda");
    }

    public void navigatingToBikesPage() {
        navigateToNewBikes();
        navigateToUpcomingSliderTab();
        clickAllUpcomingBikesLink();
        waitPageLoad();
        filterBikes();
    }

}
