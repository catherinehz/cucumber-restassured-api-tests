package com.cucumber.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import java.util.Map;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GenericController {

    protected Map<String, Object> convertObjectToMap(Object params) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> paramsMap = mapper.convertValue(params, Map.class);
        return paramsMap;
    }

    public void verifySuccessRequest(Response response) {
        assertThat(response.getStatusCode(), equalTo(SC_OK));
    }

    public void verifySuccessDeleteRequest(Response response) {
        assertThat(response.getStatusCode(), equalTo(SC_CREATED));
    }
}
