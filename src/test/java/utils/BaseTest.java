package utils;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Initialise the driver for the current test method.
        Base.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Quit the driver after the test method completes.
        Base.quitDriver();
    }



}
