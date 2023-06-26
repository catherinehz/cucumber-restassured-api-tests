package com.cucumber.controllers;

import com.cucumber.client.HttpClient;
import com.cucumber.dto_classes.BookingItemDetailsDto;
import com.cucumber.helpers.ObjectMapperHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class BookingController extends GenericController {

    private final String bookingsURL = "https://restful-booker.herokuapp.com/booking";
    private final HttpClient httpClient = new HttpClient();

    public Response getBookingIds() {
        Response response = httpClient.doGet(bookingsURL, given().accept(ContentType.JSON));
        verifySuccessRequest(response);
        return response;
    }

    public Response createBooking(BookingItemDetailsDto bookingItemDetailsDto) {
        Response response = httpClient.doPost(
                bookingsURL,
                bookingItemDetailsDto
        );
        verifySuccessRequest(response);
        return response;
    }

    public Response getBookingById(int bookingId) {
        String url = bookingsURL + "/" + bookingId;
        Response response = httpClient.doGet(url);
        verifySuccessRequest(response);
        return response;
    }

    public Response updateBooking(BookingItemDetailsDto bookingItemDetailsDto, int bookingId) {
        String url = bookingsURL + "/" + bookingId;

        String requestBody = "";
        try {
            requestBody = ObjectMapperHelper.writeObjectToJsonString(bookingItemDetailsDto);
        } catch (IOException e) {
            e.printStackTrace();
        }

        RequestSpecification requestSpec = given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=");

        Response response = httpClient.doPut(url, requestBody, requestSpec);

        verifySuccessRequest(response);
        return response;
    }

    public Response partialBookingUpdate(BookingItemDetailsDto buildPartialBooking, int bookingId) {
        String url = bookingsURL + "/" + bookingId;

        String requestBody = "";
        try {
            requestBody = ObjectMapperHelper.writeObjectToJsonString(buildPartialBooking);
        } catch (IOException e) {
            e.printStackTrace();
        }

        RequestSpecification requestSpec = given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=");

        Response response = httpClient.doPatch(url, requestBody, requestSpec);

        verifySuccessRequest(response);
        return response;
    }
}
