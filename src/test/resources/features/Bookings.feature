Feature: Booking API testing

  Scenario: Retrieve all bookings
    Given the booking API is available
    When I request all bookings
    Then I should receive a successful response 200 code

  Scenario: Create Booking
    Given the booking API is available
    When I send a POST request to the endpoint with valid booking details
    Then I should receive a successful response 200 code
    And the response body should contain the created booking details

  Scenario: Get Booking by ID
    Given the booking API is available
    When I send a POST request to the endpoint with valid booking details
    And I send a GET request to the endpoint with created booking ID
    Then I should receive a successful response 200 code
    And the response body should contain the created booking details for the specified ID

  Scenario: Update Booking
    Given the booking API is available
    When I send a POST request to the endpoint with valid booking details
    And I send a PUT request to the endpoint with updated booking details
    Then I should receive a successful response 200 code
    And the response body should contain the updated booking details

  Scenario: Partially Update Booking
    Given the booking API is available
    When I send a POST request to the endpoint with valid booking details
    And I send a PATCH request to the endpoint with the updated booking fields
    Then I should receive a successful response 200 code
    And the response body should contain the partially updated booking details

  Scenario: Delete Booking
    Given the booking API is available
    When I send a POST request to the endpoint with valid booking details
    And I send a DELETE request to the endpoint
    Then I should receive a successful response 201 code
    And the response body should confirm the deletion of the booking

  Scenario: Get Booking IDs with Filters
    Given the booking API is available
    When I send a POST request to the endpoint with valid booking details
    And I send a GET request to the endpoint with the specified filters 2014-03-13 and 2023-05-21
    Then I should receive a successful response 200 code
    And the response body should contain a filtered list of booking IDs based on the specified filters