package com.studentapp.testbase;

import com.studentapp.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;


public class TestBase {
    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init() {
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("local.URL"); //http://localhost
        RestAssured.port = Integer.parseInt(propertyReader.getProperty("student.Port")); //8080

    }

}
