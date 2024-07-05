package com.studentapp.studentinfo;

import com.studentapp.model.Datum;
import com.studentapp.model.Products;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Step;
import org.junit.BeforeClass;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ProductStep {
    static String name = "Baloon (2 pack)";
    static String type = "Soft";
    static double price = 4.99;
    static String upc = "011";
    static int shipping = 1;
    static String description = "celebrations";
    static String manufacturer = "Rubber";
    static String model = "MN2400B4Y";
    static String url = "http://www.bestbuy.com";
    static String image = "image-jpg";

    int idNumber;
    @BeforeClass
    public static void inIt(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/products";
    }

    @Step("getting product info by name:{0}")
    public ValidatableResponse getAllProduct(){
        Products products = given()
                .when()
                .get()
                .getBody().as(Products.class);
        System.out.println(products.getTotal());
        return null;
    }

    // create new product
    @Step("creating product with name :{0},type: {1},price: {2},shipping: {3},upc: {4},description: {5},manufacturer: {6},model: {7}, url: {8},image: {9}")
    public void createNewProduct(String name, String type, int price, int shipping, String upc,String description, String manufacturer, String model, String url, String image ){
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
        idNumber=datum1.getId();
    }

    // verify product created By id
    @Step("verify product created by id")
    public void verifyProductById(int productId){
        Datum datum1=given()
                .log().all()
                .pathParam("id", idNumber)
                .when()
                .get("/{id}")
                .getBody()
                .as(Datum.class);
        System.out.println(datum1.getName());
    }

    // update product
    @Step("update product by name :{0},type: {1},price: {2},shipping: {3},upc: {4},description: {5},manufacturer: {6},model: {7}, url: {8},image: {9}")
    public void updateProduct(int productId, String name, String type, int price, int upc, int shipping, String description, String manufacturer, String model, String url, String image){
        Datum datum = new Datum();
        datum.setName("party");
        datum.setType("themes");
        datum.setPrice((float) ProductStep.price);
        datum.setShipping(ProductStep.shipping);
        datum.setUpc(ProductStep.upc);
        datum.setDescription(ProductStep.description);
        datum.setManufacturer(ProductStep.manufacturer);
        datum.setModel(ProductStep.model);
        datum.setUrl(ProductStep.url);
        datum.setImage(ProductStep.image);
        Response response=given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", idNumber)
                .when()
                .body(datum)
                .put("/{id}");
        response.then().statusCode(200);
    }

    // delete by id
    @Step
    public HashMap<String, ?> deleteProductById(int productId){
        Response response=given()
                .log().all()
                .pathParam("id", idNumber)
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
        return null;
    }

    //verify product by id after delete
    @Step
    public void verifyProductDeletedById(){
        Response response=given()
                .log().all()
                .pathParam("id", idNumber)
                .when()
                .get("/{id}");
        response.then().statusCode(404);
    }
}
