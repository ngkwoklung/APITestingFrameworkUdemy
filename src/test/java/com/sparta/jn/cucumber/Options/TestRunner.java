package com.sparta.jn.cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/sparta/jn/features", glue = {"com.sparta.jn.stepDefinitions"})
public class TestRunner {
}
