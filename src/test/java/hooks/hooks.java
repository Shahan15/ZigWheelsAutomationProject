package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import utils.Base;

public class hooks {

    @Before
    public void setUp() {
        Base.getDriver();
        Base.logger.info("Webdriver in hooks class called");
    }

    @After
    public void tearDown() {
        Base.quitDriver(); // Quit WebDriver after the test
        Base.logger.info("WebDriver closed");
    }
}
