package pages;

import org.openqa.selenium.By;
import org.testng.Assert;
import utils.Base;

public class HomePage extends Base {

    public HomePage () {
        this.driver = getDriver();
    }

    public static void randomtest() {
        try {
            // Locate the ZigWheels logo element using the `alt` attribute
            By zigWheelsLogo = By.cssSelector("img[alt='Home']");
            boolean isLogoDisplayed = Base.driver.findElement(zigWheelsLogo).isDisplayed();

            // Log the result and assert if the logo is displayed
            Base.logger.info("ZigWheels logo display status: " + isLogoDisplayed);
            Assert.assertTrue(isLogoDisplayed, "ZigWheels logo is not displayed on the page!");
        } catch (Exception ex) {
            Base.logger.error("Error while verifying ZigWheels logo: " + ex.getMessage());
            Assert.fail("Test failed due to an exception.");
        }
    }

}
