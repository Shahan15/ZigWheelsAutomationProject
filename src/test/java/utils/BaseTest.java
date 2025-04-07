package utils;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Initialize the driver for the current test method.
        Base.getDriver();
        // You might want to initialize your reporting here if applicable.
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Quit the driver after the test method completes.
        Base.quitDriver();
    }



}
