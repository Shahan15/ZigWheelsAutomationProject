package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.NavigatingToBikes;
import pages.VerifyingHomePage;
import utils.Base;

public class BikeFiltering {

    private NavigatingToBikes navigatingToBikes;
    public VerifyingHomePage verifyingHomePage = new VerifyingHomePage();

    @Before
    public void setUp() {
        Base.getDriver();
        navigatingToBikes = new NavigatingToBikes();
        navigatingToBikes.init();
        Base.logger.info("Webdriver and Homepage initialised");
    }


    @Given("the user has opened the home page of the bike website")
    public void theUserIsOnHomePage()  {
        verifyingHomePage.verifyOnHomePage();
    }

    @When("they navigate to New Bikes from the homepage")
    public void theyNavigateToFromTheHomepage() {
        navigatingToBikes.navigateToNewBikes();
    }

    @And("they select Upcoming bikes")
    public void theySelect() {

    }

    @And("they choose All upcoming bikes")
    public void theyChoose() {

    }

    @And("they filter for only Honda bikes")
    public void theyFilterForOnlyHondaBikes() {

    }

    @Then("the bike list should contain only Honda bikes")
    public void theBikeListShouldContainOnlyHondaBikes() {
    }
}
