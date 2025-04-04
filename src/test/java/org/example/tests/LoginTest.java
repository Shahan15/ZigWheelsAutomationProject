package org.example.tests;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.VerifyingHomePage;
import utils.Base;
import utils.BaseTest;
import utils.ReportUtils;
import utils.TestListener;


@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
    private VerifyingHomePage verifyingHomePage;
    private LoginPage loginPage;

    @BeforeTest
    public void setUp () {
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

}
