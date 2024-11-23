package com.bookerapi.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import static com.bookerapi.config.Config.BASE_URL;

public class TestBase {
    protected RequestSpecification requestSpec;

    @BeforeClass
    public void setUp() {
        requestSpec = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setContentType(ContentType.JSON)
            .build();
        
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}