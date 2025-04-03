package org.example.tests;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.VerifyingHomePage;
import utils.Base;
import utils.ReportUtils;
import utils.TestListener;


@Listeners(TestListener.class)
public class LoginTest {
    private VerifyingHomePage verifyingHomePage;
    private LoginPage loginPage;

    @BeforeTest
    public void setUp () {
        Base.getDriver();
        ReportUtils.setUpExtentReport();
        verifyingHomePage = new VerifyingHomePage();
        loginPage = new LoginPage();
        loginPage.navigateToHomePage();
    }

    @Test
    public void googleLoginTest() {
        verifyingHomePage.clickingCookieConsentBtn();
        loginPage.attemptLogin();
    }

    @AfterTest
    public void tearDown() {
        Base.quitDriver();
    }




}
