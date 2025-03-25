package org.example.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.Base;
import utils.NavigationUtils;

public class TestCases {
    private HomePage homePage;

    @BeforeTest
    public void setup() {
        Base.getDriver();
        NavigationUtils.getTestingSiteUrl("TestingSite");
        homePage = new HomePage();
    }

    @Test
    public void HomepageTest () {
        homePage.ClickingCookieConsentBtn();
        homePage.VerifyOnHomePage();
    }

    @AfterTest
    public void tearDown() {
        Base.quitDriver();
    }


}
