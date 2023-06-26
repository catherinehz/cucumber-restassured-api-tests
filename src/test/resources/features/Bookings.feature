Feature: Booking API testing

  Scenario: Retrieve all bookings
    Given the booking API is available
    When I request all bookings
    Then I should receive a list of bookings

  Scenario: Create Booking
    Given the booking API is available
    When I send a POST request to the endpoint with valid booking details
    Then I should receive a successful response
    And the response body should contain the created booking details
