package org.example.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.NavigatingToBikes;
import utils.Base;
import utils.ReportUtils;
import utils.TestListener;

@Listeners(TestListener.class)
public class NavigatingToBikesTests {
    private NavigatingToBikes navigatingToBikes;

    @BeforeTest
    public void setup() {
        Base.getDriver();
        navigatingToBikes = new NavigatingToBikes();
        navigatingToBikes.init();
        ReportUtils.setUpExtentReport();
    }

    @Test
    public void NavigatingToBikesPageTest() {
        ReportUtils.createTest("Navigating to Bikes page Test");
        navigatingToBikes.navigatingToBikesPage();
        ReportUtils.getTest().pass("Navigated to Bikes page Successfully");
    }

    @AfterTest
    public void tearDown() {
        Base.quitDriver();
    }
}

