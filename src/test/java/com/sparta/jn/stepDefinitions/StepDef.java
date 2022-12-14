package com.sparta.jn.stepDefinitions;

import com.sparta.jn.APIResources;
import com.sparta.jn.TestDataBuild;
import com.sparta.jn.Utils;
import com.sparta.jn.pojo.AddPlace;
import com.sparta.jn.pojo.Location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StepDef extends Utils {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String placeId;
    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws FileNotFoundException {
        requestSpecification = given().spec(requestSpecification())
                .body(data.addPlacePayLoad(name, language, address));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        APIResources apiResource = APIResources.valueOf(resource);
        System.out.println(apiResource.getResource());
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        if (method.equalsIgnoreCase("POST")) {
            response = requestSpecification.when().post(apiResource.getResource());
        } else if(method.equalsIgnoreCase("GET")) {
            response = requestSpecification.when().get(apiResource.getResource());
        }
    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Then("{string} in response is {string}")
    public void in_response_is(String expectedKey, String expectedValue) {
        Assert.assertEquals(getJsonPath(response, expectedKey), expectedValue);
    }

    @Then("verfy place_Id created maps to {string} using {string}")
    public void verfy_place_id_created_maps_to_using(String expectedName, String resource) throws FileNotFoundException {

        placeId = getJsonPath(response, "place_id");
        requestSpecification = given().spec(requestSpecification()).queryParam("place_id", placeId);
        user_calls_with_http_request(resource, "GET");
        String actualName = getJsonPath(response, "name");
        Assert.assertEquals(expectedName,actualName);
    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws FileNotFoundException {
        requestSpecification = given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
    }

}
