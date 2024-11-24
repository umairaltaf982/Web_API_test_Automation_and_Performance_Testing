package com.bookerapi.utils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import com.bookerapi.model.AuthRequest;
import com.bookerapi.config.TestConfig;
import static com.bookerapi.constants.ApiEndpoints.AUTH;

public class TokenManager {
    public static String getToken() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("admin");
        authRequest.setPassword("password123");

        Response response = given()
                .spec(TestConfig.getRequestSpec())
                .body(authRequest)
                .when()
                .post(AUTH)
                .then()
                .statusCode(200)
                .extract().response();

        return response.jsonPath().getString("token");
    }
}
