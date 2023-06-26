package com.cucumber.controllers;

import com.cucumber.client.HttpClient;
import com.cucumber.dto_classes.BookingItemDetailsDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingController extends GenericController {

    private final String bookingsURL = "https://restful-booker.herokuapp.com/booking";
    private final HttpClient httpClient = new HttpClient();

    public Response getBookingIds() {
        Response response = httpClient.doGet(bookingsURL, given().accept(ContentType.JSON));
        verifySuccessRequest(response);
        return response;
    }

    public Response createBooking(BookingItemDetailsDto bookingItemDto) {
        Response response = httpClient.doPost(
                bookingsURL,
                bookingItemDto
        );
        verifySuccessRequest(response);
        return response;
    }
}
