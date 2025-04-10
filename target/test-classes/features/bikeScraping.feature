@bikeScraping
Feature: Scraping bike data and filtering

  Scenario: Verify bike data scraping and save functionality
    Given the system navigates to the filtered bikes page
    When the system scrapes bike data
    Then the data should be saved to an Excel Sheet
