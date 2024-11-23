package com.bookerapi.tests;

import com.bookerapi.base.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class BookingTest extends TestBase {

    @Test
    public void getAllBookingIds() {
        Response response = given()
            .spec(requestSpec)
        .when()
            .get("/booking")
        .then()
            .statusCode(200)
            .extract().response();
    }

    @Test
    public void createNewBooking() {
        String requestBody = """
            {
                "firstname": "Jim",
                "lastname": "Brown",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2023-01-01",
                    "checkout": "2023-01-02"
                },
                "additionalneeds": "Breakfast"
            }""";

        Response response = given()
            .spec(requestSpec)
            .body(requestBody)
        .when()
            .post("/booking")
        .then()
            .statusCode(200)
            .extract().response();
    }
}