package org.example.tests;


import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.VerifyingHomePage;
import utils.Base;
import utils.BaseTest;
import utils.ReportUtils;
import utils.TestListener;
import static utils.ReportUtils.extent;


@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
    private VerifyingHomePage verifyingHomePage;
    private LoginPage loginPage;

    @BeforeTest
    public void setUp () {
        ReportUtils.setUpExtentReport(this.getClass().getSimpleName());
        TestListener.setExtent(extent);

        verifyingHomePage = new VerifyingHomePage();
        loginPage = new LoginPage();
        loginPage.navigateToHomePage();
    }

    @Test
    public void googleLoginTest() {
        ((JavascriptExecutor) Base.getDriver()).executeScript("window.focus();");

        verifyingHomePage.clickingCookieConsentBtn();
        loginPage.attemptLogin();
    }

}
