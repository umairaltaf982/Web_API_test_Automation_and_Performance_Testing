package com.bookerapi.config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import static com.bookerapi.constants.ApiEndpoints.BASE_URL;

public class TestConfig {
    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType("application/json")
                .build();
    }
}