Feature: Booking API testing

  Scenario: Retrieve all bookings
    Given the booking API is available
    When I request all bookings
    Then I should receive a successful response

  Scenario: Create Booking
    Given the booking API is available
    When I send a POST request to the endpoint with valid booking details
    Then I should receive a successful response
    And the response body should contain the created booking details

  Scenario: Get Booking by ID
    Given the booking API is available
    When I send a POST request to the endpoint with valid booking details
    And I send a GET request to the endpoint with created booking ID
    Then I should receive a successful response
    And the response body should contain the created booking details for the specified ID