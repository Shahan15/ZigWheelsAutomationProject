package org.example.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.NavigatingToBikes;
import utils.BaseTest;
import utils.ReportUtils;
import utils.TestListener;
import static utils.ReportUtils.extent;

@Listeners(TestListener.class)
public class NavigatingToBikesTests extends BaseTest {
    private NavigatingToBikes navigatingToBikes;

    @BeforeTest
    public void setup() {
        ReportUtils.setUpExtentReport(this.getClass().getSimpleName());
        TestListener.setExtent(extent);

        navigatingToBikes = new NavigatingToBikes();
        navigatingToBikes.navigateToHomePage();
    }

    @Test
    public void NavigatingToBikesPageTest() {
        navigatingToBikes.navigatingToBikesPage();
        ReportUtils.getTest().pass("Navigated to Bikes page Successfully");
    }

}

