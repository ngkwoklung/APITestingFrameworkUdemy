package com.sparta.jn.stepDefinitions;

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
    @Given("Add Place Payload")
    public void add_place_payload() throws FileNotFoundException {
        requestSpecification = given().spec(requestSpecification())
                .body(data.addPlacePayLoad());
    }

    @When("user calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
        response = requestSpecification.
                when()
                    .post("/maps/api/place/add/json").
                then()
                    .spec(responseSpecification)
                    .extract()
                    .response();
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
