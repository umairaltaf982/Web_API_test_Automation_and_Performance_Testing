package com.restful01.tests;

import com.restful01.models.Device;
import com.restful01.models.DeviceData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DeviceApiTests extends BaseTest {
String createdDeviceId;
    @Test(description = "Get all devices")
    public void testGetAllDevices() {
        given()
                .when()
                .get("/objects")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test(description = "Get specific devices by IDs")
    public void testGetDevicesByIds() {
        given()
                .queryParam("id", "3")
                .queryParam("id", "5")
                .when()
                .get("/objects")
                .then()
                .statusCode(200)
                .body("size()", equalTo(2))
                .body("id", hasItems("3", "5"));
    }

    @Test(description = "Get single device")
    public void testGetSingleDevice() {
        given()
                .pathParam("id", "7")
                .when()
                .get("/objects/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo("7"))
                .body("name", equalTo("Apple MacBook Pro 16"));
    }

   
    @Test(description = "Create new device")
    public void testCreateDevice() {
        Device device = new Device();
        device.setName("Test Device");
        DeviceData data = new DeviceData();
        data.setColor("Black");
        data.setCapacity("128 GB");
        device.setData(data);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(device)
                .when()
                .post("/objects")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("createdAt", notNullValue())
                .extract().response();

        // Store the created ID for subsequent tests
         createdDeviceId = response.jsonPath().getString("id");
    }

    @Test(description = "Update device", dependsOnMethods = "testCreateDevice")
    public void testUpdateDevice() {
        Device device = new Device();
        device.setName("Updated Test Device");
        DeviceData data = new DeviceData();
        data.setColor("White");
        data.setCapacity("256 GB");
        device.setData(data);

        given()
                .contentType(ContentType.JSON)
                .body(device)
                .pathParam("id", createdDeviceId)
                .when()
                .put("/objects/{id}")
                .then()
                .statusCode(200)
                .body("updatedAt", notNullValue())
                .body("name", equalTo("Updated Test Device"));
    }

    @Test(description = "Delete device", dependsOnMethods = {"testCreateDevice", "testUpdateDevice"})
    public void testDeleteDevice() {
        given()
                .pathParam("id", createdDeviceId)
                .when()
                .delete("/objects/{id}")
                .then()
                .statusCode(200)
                .body("message", containsString("has been deleted"));
    }
}