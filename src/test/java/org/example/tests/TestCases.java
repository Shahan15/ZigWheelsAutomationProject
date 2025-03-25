package org.example.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.Base;

public class TestCases {
    HomePage homePage;

    @BeforeTest
    public void setup() {
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
