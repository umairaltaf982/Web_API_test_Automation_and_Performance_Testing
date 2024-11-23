package com.restfulbooker.models;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected static String token;
    private static final String BASE_URL = "https://restful-booker.herokuapp.com";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
        generateToken();
    }

    private void generateToken() {
        String payload = """
                {
                    "username": "admin",
                    "password": "password123"
                }
                """;

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/auth")
                .then()
                .extract()
                .response();

        token = response.jsonPath().getString("token");
    }
}