package com.restfulbooker.models;

import com.restfulbooker.models.Booking;
import com.restfulbooker.models.BookingDates;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.restfulbooker.models.BaseTest.token;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class BookingTests extends BaseTest {
    private Integer bookingId;

    @BeforeClass
    public void createTestBooking() {
        BookingDates bookingDates = new BookingDates(
                "2024-01-01",
                "2024-01-05"
        );

        Booking booking = new Booking(
                "Umair",
                "Altaf",
                150,
                true,
                bookingDates,
                "Breakfast"
        );

        Response response = given()
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .response();

        bookingId = response.jsonPath().getInt("bookingid");
    }

    @Test(priority = 1)
    public void testCreateBooking() {
        BookingDates bookingDates = new BookingDates(
                "2024-01-01",
                "2024-01-05"
        );

        Booking booking = new Booking(
                "Umair",
                "Altaf",
                150,
                true,
                bookingDates,
                "Breakfast"
        );

        Response response = given()
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .response();

        int newBookingId = response.jsonPath().getInt("bookingid");
        assertTrue(newBookingId > 0);
    }

    @Test(priority = 2)
    public void testGetBooking() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .response();

        String firstname = response.jsonPath().getString("firstname");
        assertNotNull(firstname);
        assertEquals(firstname, "Umair");
    }

    @Test(priority = 3)
    public void testUpdateBooking() {
        BookingDates bookingDates = new BookingDates(
                "2024-02-01",
                "2024-02-05"
        );

        Booking updatedBooking = new Booking(
                "Anas",
                "Altaf",
                200,
                true,
                bookingDates,
                "Dinner"
        );

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(updatedBooking)
                .when()
                .put("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .response();

        String firstname = response.jsonPath().getString("firstname");
        assertEquals(firstname, "Anas");
    }

    @Test(priority = 4)
    public void testPartialUpdateBooking() {
        String payload = """
                {
                    "firstname": "Anas",
                    "totalprice": 250
                }
                """;

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(payload)
                .when()
                .patch("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .response();

        String firstname = response.jsonPath().getString("firstname");
        int totalPrice = response.jsonPath().getInt("totalprice");
        assertEquals(firstname, "Anas");
        assertEquals(totalPrice, 250);
    }

    @Test(priority = 5)
    public void testDeleteBooking() {
        given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + bookingId)
                .then()
                .statusCode(201);

        // Verify booking is deleted
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(404);
    }
}