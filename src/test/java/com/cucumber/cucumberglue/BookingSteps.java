package com.cucumber.cucumberglue;

import com.cucumber.controllers.BookingController;
import com.cucumber.dto_classes.BookingIdDto;
import com.cucumber.dto_classes.BookingItemDetailsDto;
import com.cucumber.dto_classes.BookingItemDto;
import com.cucumber.helpers.AssertsHelper;
import com.cucumber.helpers.BookingBuildersHelper;
import com.cucumber.helpers.ScenarioContext;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;

import static com.cucumber.helpers.ScenarioContext.RESPONSE;

public class BookingSteps extends GeneralSteps {
    private ScenarioContext scenarioContext;
    private Response response;
    private BookingIdDto bookingIdDto;
    private BookingItemDetailsDto bookingItemDetailsDto = new BookingItemDetailsDto();
    private BookingController bookingController = new BookingController();
    private List<BookingIdDto> bookingIdDtoList;

    public BookingSteps() {
    }

    public BookingSteps(ScenarioContext context) {
        this.scenarioContext = context;
    }

    @Given("the booking API is available")
    public void theBookingApiIsAvailable() {
        Response response = bookingController.getBookingIds();
        Assert.assertEquals(200, response.getStatusCode());
        scenarioContext().putInStore(RESPONSE, response);
    }

    @When("I request all bookings")
    public void requestAllBookings() throws IOException {
        Response response = bookingController.getBookingIds();
        String responseBody = response.getBody().asString();
        ObjectMapper objectMapper = new ObjectMapper();
        bookingIdDtoList = objectMapper.readValue(responseBody, new TypeReference<List<BookingIdDto>>() {
        });
    }

    @When("I send a POST request to the endpoint with valid booking details")
    public void sendAPostRequestToTheEndpointWithValidBookingDetails() {
        bookingItemDetailsDto = BookingBuildersHelper.buildValidBooking();
        Response response = bookingController.createBooking(bookingItemDetailsDto);
        scenarioContext().putInStore(RESPONSE, response);
        scenarioContext().putInStore("createdBooking", bookingItemDetailsDto);
    }

    @Then("I should receive a successful response")
    public void iShouldReceiveASuccessfulResponse() {
        int statusCode = scenarioContext().getFromStore(RESPONSE, Response.class).getStatusCode();
        Assert.assertEquals(200, statusCode);
    }

    @And("^the response body should contain the created booking details$")
    public void theResponseBodyShouldContainTheCreatedBookingDetails() {
        BookingItemDetailsDto expectedBooking = scenarioContext().getFromStore("createdBooking", BookingItemDetailsDto.class);
        BookingItemDetailsDto createdBooking = scenarioContext().getFromStore(RESPONSE, Response.class)
                .as(BookingItemDto.class).getBooking();

        AssertsHelper.verifyDetailsFor(expectedBooking, createdBooking);
    }

    @And("I send a GET request to the endpoint with created booking ID")
    public void sendAGetRequestToTheEndpointWithCreatedBookingID() {
        BookingItemDto createdBooking = scenarioContext().getFromStore(RESPONSE, Response.class).as(BookingItemDto.class);
        int bookingId = createdBooking.getBookingid();

        Response response = bookingController.getBookingById(bookingId);
        Assert.assertEquals(200, response.getStatusCode());
        scenarioContext().putInStore(RESPONSE, response);
    }

    @And("^the response body should contain the created booking details for the specified ID$")
    public void theResponseBodyShouldContainTheCreatedBookingDetailsForSpecifiedId() {
        BookingItemDetailsDto expectedBooking = scenarioContext()
                .getFromStore("createdBooking", BookingItemDetailsDto.class);
        BookingItemDetailsDto createdBooking = scenarioContext()
                .getFromStore(RESPONSE, Response.class).as(BookingItemDetailsDto.class);

        AssertsHelper.verifyDetailsFor(expectedBooking, createdBooking);
    }

    @And("I send a PUT request to the endpoint with updated booking details")
    public void sendAPutRequestToTheEndpointWithUpdatedBookingDetails() {
        BookingItemDto createdBooking = scenarioContext().getFromStore(RESPONSE, Response.class).as(BookingItemDto.class);
        int bookingId = createdBooking.getBookingid();

        BookingItemDetailsDto updatedBookingItemDetailsDto = BookingBuildersHelper.buildValidBooking();

        Response response = bookingController.updateBooking(updatedBookingItemDetailsDto, bookingId);
        scenarioContext().putInStore(RESPONSE, response);
        scenarioContext().putInStore("updatedBooking", updatedBookingItemDetailsDto);
    }

    @And("the response body should contain the updated booking details")
    public void theResponseBodyShouldContainTheUpdatedBookingDetails() {
        BookingItemDetailsDto expectedBooking = scenarioContext()
                .getFromStore("updatedBooking", BookingItemDetailsDto.class);
        BookingItemDetailsDto createdBooking = scenarioContext()
                .getFromStore(RESPONSE, Response.class).as(BookingItemDetailsDto.class);

        AssertsHelper.verifyDetailsFor(expectedBooking, createdBooking);
    }

    @And("I send a PATCH request to the endpoint with the updated booking fields")
    public void sendAPatchRequestToTheEndpointWithTheUpdatedBookingFields() {
        BookingItemDto createdBooking = scenarioContext().getFromStore(RESPONSE, Response.class).as(BookingItemDto.class);
        int bookingId = createdBooking.getBookingid();

        BookingItemDetailsDto buildPartialBooking = BookingBuildersHelper.buildPartialBooking();
        Response response = bookingController.partialBookingUpdate(buildPartialBooking, bookingId);
        scenarioContext().putInStore(RESPONSE, response);
        scenarioContext().putInStore("partialUpdatedBooking", buildPartialBooking);
    }

    @And("the response body should contain the partially updated booking details")
    public void theResponseBodyShouldContainThePartiallyUpdatedBookingDetails() {
        BookingItemDetailsDto expectedBooking = scenarioContext()
                .getFromStore("partialUpdatedBooking", BookingItemDetailsDto.class);
        BookingItemDetailsDto createdBooking = scenarioContext()
                .getFromStore(RESPONSE, Response.class).as(BookingItemDetailsDto.class);

        Assert.assertEquals(expectedBooking.getFirstname(), createdBooking.getFirstname());
        Assert.assertEquals(expectedBooking.getLastname(), createdBooking.getLastname());
    }
}
