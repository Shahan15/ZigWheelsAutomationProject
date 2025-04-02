Feature: Bike Filtering Page
  Scenario: Navigation and Filtering for Honda Bikes
    Given the user has opened the home page of the bike website
    When they navigate to New Bikes from the homepage
    And they select Upcoming bikes
    And they choose All upcoming bikes
    And they filter for only Honda bikes
    Then the bike list should contain only Honda bikes

    #this is the feature file this defines the feature you want to test.
    # here we are testing Login