package org.example.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BikesPage;
import utils.Base;
import utils.ReportUtils;
import utils.TestListener;

@Listeners(TestListener.class)
public class BikesPageTests {
    private BikesPage bikesPage;

    @BeforeTest
    public void setup() {
        Base.getDriver();
        bikesPage = new BikesPage();
        ReportUtils.setUpExtentReport();
    }

    @Test
    public void NavigatingToBikesPageTest() {
        ReportUtils.createTest("Navigating to Bikes page Test");
        bikesPage.navigatingToBikesPage();
        ReportUtils.getTest().pass("Navigated to Bikes page Successfully");
    }

    @AfterTest
    public void tearDown() {
        Base.quitDriver();
    }
}

