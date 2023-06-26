package com.cucumber.controllers;

import io.restassured.response.Response;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GenericController {

    public void verifySuccessRequest(Response response) {
        assertThat(response.getStatusCode(), equalTo(SC_OK));
    }
}
