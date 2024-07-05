package com.studentapp.productinfo;

import com.studentapp.model.Datum;
import com.studentapp.model.Products;
import com.studentapp.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static io.restassured.RestAssured.given;

@RunWith(SerenityRunner.class)
public class ProductCRUDTest extends TestBase {

    static String name = "Balloon (2 pack)";
    static String type = "Soft";
    static double price = 4.99;
    static String upc = "011";
    static int shipping = 1;
    static String description = "celebrations";
    static String manufacturer = "Rubber";
    static String model = "MN2400B4Y";
    static String url = "http://www.bestbuy.com";
    static String image = "image-jpg";

    static int productId;

    @BeforeClass
    public void inIt(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/products";
    }

    @Test
    public void getAllProduct(){
        Products products = given()
                .when()
                .get()
                .getBody().as(Products.class);
        System.out.println(products.getTotal());
    }

    // create new product
    @Test
    public void createNewProduct(){
        Datum datum = new Datum();
        datum.setName(name);
        datum.setType(type);
        datum.setPrice((float) price);
        datum.setShipping(shipping);
        datum.setUpc(upc);
        datum.setDescription(description);
        datum.setManufacturer(manufacturer);
        datum.setModel(model);
        datum.setUrl(url);
        datum.setImage(image);
        Datum datum1=given()
                .log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(datum)
                .post()
                .getBody()
                .as(Datum.class);
        System.out.println(datum1.getId());
        productId=datum1.getId();
    }

    // verify product created By id
    @Test
    public void verifyProductById(){
        Datum datum1=given()
                .log().all()
                .pathParam("id", productId)
                .when()
                .get("/{id}")
                .getBody()
                .as(Datum.class);
        System.out.println(datum1.getName());
    }

    // update product
    @Test
    public void updateProduct(){
        Datum datum = new Datum();
        datum.setName("party");
        datum.setType("themes");
        datum.setPrice((float)price);
        datum.setShipping(shipping);
        datum.setUpc(upc);
        datum.setDescription(description);
        datum.setManufacturer(manufacturer);
        datum.setModel(model);
        datum.setUrl(url);
        datum.setImage(image);
        Response response=given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", productId)
                .when()
                .body(datum)
                .put("/{id}");
        response.then().statusCode(200);
    }

    // delete by id
    @Test
    public void deleteProductById(){
        Response response=given()
                .log().all()
                .pathParam("id", productId)
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
    }

    //verify product by id after delete
    @Test
    public void verifyProductDeletedById(){
        Response response=given()
                .log().all()
                .pathParam("id", productId)
                .when()
                .get("/{id}");
        response.then().statusCode(404);
    }
}
