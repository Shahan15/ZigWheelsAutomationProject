@Login
Feature: User Login

  Scenario: Successful login
    Given the user is on the home page to Login
    When they click Login
    And they click on Google Login
    And they enter email or phone number
    And they click on the next Button
    Then they should be redirected to an error page and screenshot taken
