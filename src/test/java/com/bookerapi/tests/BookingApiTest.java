package com.bookerapi.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

import com.bookerapi.config.TestConfig;
import com.bookerapi.model.Booking;
import com.bookerapi.utils.TestDataGenerator;
import static com.bookerapi.constants.ApiEndpoints.*;

public class BookingApiTest extends BaseTest {

    @Test
    public void testGetAllBookings() {
        Response response = given()
            .spec(TestConfig.getRequestSpec())
        .when()
            .get(BOOKING)
        .then()
            .statusCode(200)
            .extract().response();

        assertNotNull(response.jsonPath().getList("bookingid"));
    }

    @Test
    public void testCreateAndRetrieveBooking() {
        // Create booking
        Booking newBooking = TestDataGenerator.createTestBooking();
        
        Response createResponse = given()
            .spec(TestConfig.getRequestSpec())
            .body(newBooking)
        .when()
            .post(BOOKING)
        .then()
            .statusCode(200)
            .extract().response();
        
        Integer bookingId = createResponse.jsonPath().getInt("bookingid");
        
        // Get booking
        Response getResponse = given()
            .spec(TestConfig.getRequestSpec())
        .when()
            .get(BOOKING + "/" + bookingId)
        .then()
            .statusCode(200)
            .extract().response();
        
        assertEquals(getResponse.jsonPath().getString("firstname"), newBooking.getFirstname());
    }

    @Test
    public void testUpdateBooking() {
        // Create booking first
        Booking booking = TestDataGenerator.createTestBooking();
        Response createResponse = given()
            .spec(TestConfig.getRequestSpec())
            .body(booking)
        .when()
            .post(BOOKING)
        .then()
            .statusCode(200)
            .extract().response();
        
        Integer bookingId = createResponse.jsonPath().getInt("bookingid");
        
        // Update booking
        booking.setFirstname("Jane");
        Response updateResponse = given()
            .spec(TestConfig.getRequestSpec())
            .header("Cookie", "token=" + token)
            .body(booking)
        .when()
            .put(BOOKING + "/" + bookingId)
        .then()
            .statusCode(200)
            .extract().response();
        
        assertEquals(updateResponse.jsonPath().getString("firstname"), "Jane");
    }

    @Test
    public void testDeleteBooking() {
        // Create booking first
        Booking booking = TestDataGenerator.createTestBooking();
        Response createResponse = given()
            .spec(TestConfig.getRequestSpec())
            .body(booking)
        .when()
            .post(BOOKING)
        .then()
            .statusCode(200)
            .extract().response();
        
        Integer bookingId = createResponse.jsonPath().getInt("bookingid");
        
        // Delete booking
        given()
            .spec(TestConfig.getRequestSpec())
            .header("Cookie", "token=" + token)
        .when()
            .delete(BOOKING + "/" + bookingId)
        .then()
            .statusCode(201);
            
        // Verify deletion
        given()
            .spec(TestConfig.getRequestSpec())
        .when()
            .get(BOOKING + "/" + bookingId)
        .then()
            .statusCode(404);
    }
}