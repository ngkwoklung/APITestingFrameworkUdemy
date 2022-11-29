package com.sparta.jn.stepDefinitions;

import io.cucumber.java.Before;

import java.io.FileNotFoundException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws FileNotFoundException {
        StepDef stepDef = new StepDef();
        if (StepDef.placeId == null) {
            stepDef.add_place_payload_with("Ng", "English", "UK");
            stepDef.user_calls_with_http_request("addPlaceAPI", "POST");
            stepDef.verfy_place_id_created_maps_to_using("Ng", "getPlaceAPI");
        }
    }
}
