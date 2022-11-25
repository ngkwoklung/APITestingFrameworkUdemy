package com.sparta.jn.stepDefinitions;

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

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StepDef {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;
    @Given("Add Place Payload")
    public void add_place_payload() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        AddPlace p =new AddPlace();
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setLanguage("French-IN");
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName("Frontline house");
        List<String> myList =new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);
        Location l =new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);

        RequestSpecification req = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        requestSpecification = given().spec(req)
                .body(p);

    }

    @When("user calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {
        response = requestSpecification.when().post("/maps/api/place/add/json").
                then().spec(responseSpecification).extract().response();
    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Then("{string} in response is {string}")
    public void in_response_is(String expectedKey, String expectedValue) {
        String strResp = response.asString();
        JsonPath jsonPath = new JsonPath(strResp);
        Assert.assertEquals(jsonPath.get(expectedKey).toString(), expectedValue);
    }

}
