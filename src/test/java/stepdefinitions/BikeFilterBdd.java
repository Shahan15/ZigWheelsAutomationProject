package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.NavigatingToBikes;
import pages.VerifyingHomePage;
import utils.Base;

public class BikeFilterBdd {

    private NavigatingToBikes navigatingToBikes;
    private VerifyingHomePage verifyingHomePage;

    // Initialise page objects in the constructor
    public BikeFilterBdd() {
        verifyingHomePage = new VerifyingHomePage();
        navigatingToBikes = new NavigatingToBikes();
    }

    @Given("the user is on the home page to navigate to bikes")
    public void theUserIsOnHomePage() {
        navigatingToBikes.navigateToHomePage(); // Perform homepage navigation here
        verifyingHomePage.verifyOnHomePage();  // Verify homepage elements
        Base.logger.info("User is now on the homepage.");
    }

    @When("they navigate to New Bikes from the homepage")
    public void theyNavigateToFromTheHomepage() {
        navigatingToBikes.navigateToNewBikes();
        Base.logger.info("Navigated to New Bikes page.");
    }

    @And("they select Upcoming bikes")
    public void theySelect() {
        navigatingToBikes.navigateToUpcomingSliderTab();
        Base.logger.info("Selected Upcoming Bikes tab.");
    }

    @And("they choose All upcoming bikes")
    public void theyChoose() {
        navigatingToBikes.clickAllUpcomingBikesLink();
        Base.logger.info("Chose All Upcoming Bikes.");
    }

    @And("they filter for only Honda bikes")
    public void theyFilterForOnlyHondaBikes() {
        navigatingToBikes.filterBikes();
        Base.logger.info("Filtered for Honda bikes.");
    }

    @Then("the bike list should contain only Honda bikes")
    public void theBikeListShouldContainOnlyHondaBikes() {
        navigatingToBikes.validatingOnlyHondaBikes();
        Base.logger.info("Validated that the bike list contains only Honda bikes.");
    }
}
