package com.bookerapi.base;

import org.testng.annotations.BeforeClass;
import com.bookerapi.utils.TokenManager;

public class BaseTest {
    protected String token;
    
    @BeforeClass
    public void setUp() {
        token = TokenManager.getToken();
    }
}