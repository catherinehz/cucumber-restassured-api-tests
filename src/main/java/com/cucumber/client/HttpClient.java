package com.cucumber.client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HttpClient {

    public HttpClient() {
        RestAssured.defaultParser = Parser.JSON;
    }

    public RequestSpecification requestSetup() {
        RestAssured.reset();
        return RestAssured.given();
    }

    public Response doGet(String url) {
        return this.doRequest(this.prepareRequest(true), Method.GET, url);
    }

    public Response doGet(String url, RequestSpecification reqSpec) {
        return this.doRequest(this.prepareRequest(reqSpec, true), Method.GET, url);
    }

    public Response doPost(String url, Object body) {
        return this.doRequest(this.prepareRequest(body, true), Method.POST, url);
    }

    public Response doPut(String url, Object body, RequestSpecification reqSpec) {
        return this.doRequest(this.prepareRequest(body, reqSpec, true), Method.PUT, url);
    }

    public Response doPatch(String url, Object body, RequestSpecification reqSpec) {
        return this.doRequest(this.prepareRequest(body, reqSpec, true), Method.PATCH, url);
    }

    public Response doDelete(String url, RequestSpecification reqSpec) {
        return this.doRequest(this.prepareRequest(reqSpec, true), Method.DELETE, url);
    }

    private RequestSpecification prepareRequest(boolean isDefaultContentType) {
        return isDefaultContentType ? this.requestSetup().contentType(ContentType.JSON) : this.requestSetup();
    }

    private RequestSpecification prepareRequest(RequestSpecification reqSpec, boolean isDefaultContentType) {
        return reqSpec.spec(this.prepareRequest(isDefaultContentType));
    }

    private RequestSpecification prepareRequest(Object body, boolean isDefaultContentType) {
        return this.prepareRequest(isDefaultContentType).body(body);
    }

    private RequestSpecification prepareRequest(Object body, RequestSpecification reqSpec, boolean isDefaultContentType) {
        return reqSpec.spec(this.prepareRequest(isDefaultContentType).body(body));
    }

    private Response doRequest(RequestSpecification requestSpecification, Method method, String url) {
        return (Response) requestSpecification.header("Content-Type", ContentType.JSON).when().request(method, url, new Object[0]);
    }
}
