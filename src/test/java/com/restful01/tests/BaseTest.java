package com.restful01.tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected static final String BASE_URL = "https://api.restful-api.dev";
    
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }
}