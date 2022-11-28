package com.sparta.jn;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    static RequestSpecification req;

    public RequestSpecification requestSpecification() throws FileNotFoundException {
        if(req == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder()
                    .setBaseUri(getGlobalValue("baseURL"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();

            return req;
        }
        return req;
    }

    public String getGlobalValue(String key) {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\kwokl\\Udemy\\" +
                    "Rest API Testing (Automation) from Scratch-Rest Assured Java\\APITestingFrameworkUdemy\\src\\test\\" +
                    "resources\\global.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    public String getJsonPath(Response response, String key) {

        String strResp = response.asString();
        JsonPath jsonPath = new JsonPath(strResp);
        return jsonPath.get(key).toString();
    }
}
