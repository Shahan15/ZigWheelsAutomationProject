package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.VerifyingHomePage;
import utils.Base;

public class LoginBdd {

    private VerifyingHomePage verifyingHomePage;
    private LoginPage loginPage;

    // Initialize page objects in the constructor
    public LoginBdd() {
        verifyingHomePage = new VerifyingHomePage();
        loginPage = new LoginPage();
    }

    @Given("the user is on the home page to Login")
    public void theUserIsOnHomePage() {
        loginPage.navigateToHomePage(); // Navigate to the homepage here
        verifyingHomePage.verifyOnHomePage(); // Verify homepage elements
        Base.logger.info("User is on the homepage for login.");
    }

    @When("they click Login")
    public void theyClickLogin() {
        loginPage.clickLoginButton();
        Base.logger.info("Login button clicked.");
    }

    @And("they click on Google Login")
    public void theyClickOnGoogleLogin() {
        loginPage.clickGoogleLogin();
        Base.logger.info("Google Login clicked.");
    }

    @And("they enter email or phone number")
    public void theyEnterEmailOrPhoneNumber() {
        loginPage.setEmailInputField();
        Base.logger.info("Email or phone number entered.");
    }

    @And("they click on the next Button")
    public void theyClickOnTheNextButton() {
        loginPage.clickNextBtn();
        Base.logger.info("Next button clicked.");
    }

    @Then("they should be redirected to an error page and screenshot taken")
    public void theyShouldBeRedirectedToAnErrorPage() {
        loginPage.waitForPageAndSS();
        Base.logger.info("Error page validated and screenshot taken.");
    }
}
