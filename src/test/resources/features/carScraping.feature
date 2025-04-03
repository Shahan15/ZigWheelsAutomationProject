@carScraping
Feature: Filtering car data and scraping

  Scenario: Verify car data scraping and save functionality
    Given the system navigates to the used cars page
    And the system filters by max price
    And the system filters by only Honda cars
    When the system scrapes car data
    Then the data should be saved to a filtered cars Excel Sheet
