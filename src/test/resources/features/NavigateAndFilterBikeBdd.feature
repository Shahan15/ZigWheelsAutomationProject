@FilteredBikes
Feature: Bike Filtering Page

  Scenario: Navigation and Filtering for Honda Bikes
    Given the user is on the home page to navigate to bikes
    When they navigate to New Bikes from the homepage
    And they select Upcoming bikes
    And they choose All upcoming bikes
    And they filter for only Honda bikes
    Then the bike list should contain only Honda bikes

    #this is the feature file this defines the feature you want to test.
    # here we are testing Login


