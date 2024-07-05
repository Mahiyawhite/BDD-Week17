package com.studentapp.testbase;

import com.studentapp.constants.Path;
import com.studentapp.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;


public class TestBaseBestBuyApi {
    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init() {
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("bestBuy.BaseUrl");
        RestAssured.port = Integer.parseInt(propertyReader.getProperty("bestBuy.Port"));
        RestAssured.basePath = Path.PRODUCTS;
    }

}
