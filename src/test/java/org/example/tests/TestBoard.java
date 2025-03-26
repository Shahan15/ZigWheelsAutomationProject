package org.example.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.BikesPage;
import pages.HomePage;
import utils.Base;
import utils.NavigationUtils;

public class TestBoard {
    private HomePage homePage;
    private BikesPage bikesPage;

    @BeforeTest
    public void setup() {
        Base.getDriver();
        NavigationUtils.getTestingSiteUrl("HomePage");
        homePage = new HomePage();
        bikesPage = new BikesPage();
    }

    @Test
    public void homepageTest () {
        homePage.clickingCookieConsentBtn();
        homePage.verifyOnHomePage();
    }

    @Test
    public void NavigatingToBikesPageTest () {
        homePage.clickingCookieConsentBtn();
        bikesPage.navigatingToBikesPage();
    }

    @AfterTest
    public void tearDown() {
        Base.quitDriver();
    }


}
