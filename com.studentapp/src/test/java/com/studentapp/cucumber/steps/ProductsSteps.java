package com.studentapp.cucumber.steps;

import com.studentapp.studentinfo.ProductStep;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

public class ProductsSteps {

    static ValidatableResponse response;

    static String name = null;
    static String type = null;
    static int price;
    static int upc;
    static int shipping;
    static String description = null;
    static String manufacturer = null;
    static String model = null;
    static String url = null;
    static String image;
    static int productId;

    @Steps
    ProductStep productStep;

    @When("^I create a new product by providing the information name \"([^\"]*)\" type \"([^\"]*)\" price \"([^\"]*)\" upc \"([^\"]*)\" shipping \"([^\"]*)\" description \"([^\"]*)\" manufacturer \"([^\"]*)\" model \"([^\"]*)\" url \"([^\"]*)\" image \"([^\"]*)\"$")
    public void iCreateANewProductByProvidingTheInformationNameTypePriceUpcShippingDescriptionManufacturerModelUrlImage(String _name, String _type, int _price, int _shipping, String _upc,String _description, String _manufacturer, String _model, String _url, String _image) throws Throwable {
        productStep.createNewProduct(name,type,price,upc, String.valueOf(shipping),description,manufacturer,model,url,image);
        response.statusCode(201);
    }

    @Then("^I verify that the product with \"([^\"]*)\" is created$")
    public void iVerifyThatTheProductWithIsCreated(String arg0) throws Throwable {
        productStep.verifyProductById(productId);
    }

    @When("^I update the product with information firstName name \"([^\"]*)\" type \"([^\"]*)\" price \"([^\"]*)\" upc \"([^\"]*)\" shipping \"([^\"]*)\" description \"([^\"]*)\" manufacturer \"([^\"]*)\" model \"([^\"]*)\" url \"([^\"]*)\" image \"([^\"]*)\"$")
    public void iUpdateTheProductWithInformationFirstNameNameTypePriceUpcShippingDescriptionManufacturerModelUrlImage(String _name, String _type, int _price, int _shipping, String _upc,String _description, String _manufacturer, String _model, String _url, String _image) throws Throwable {
        name = name + "update";
        productStep.updateProduct(productId,name,type,price,upc,shipping,description,manufacturer,model,url,image);
        response.statusCode(200);
    }

    @Then("^I delete the product data$")
    public void iDeleteTheProductData() {
        HashMap<String, ?> studentMap = productStep.deleteProductById(productId);
        Assert.assertEquals(studentMap,productId);
    }


}
