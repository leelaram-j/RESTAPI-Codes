package com.rest.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BasicsExtractResponse
{
    String bodyPost="{\n" +
            "\"location\":{\n" +
            " \"lat\" : -38.383495,\n" +
            " \"lng\" : 33.427362\n" +
            " },\n" +
            " \"accuracy\":50,\n" +
            " \"name\":\"Frontline house\",\n" +
            " \"phone_number\":\"(+91) 983 893 3937\",\n" +
            " \"address\" : \"29, side layout, cohen 09\",\n" +
            " \"types\": [\"shoe park\",\"shop\"],\n" +
            " \"website\" : \"http://google.com\",\n" +
            " \"language\" : \"Spain:ESP\"\n" +
            "}";

    @Test
    public void extractResponse()
    {
        RestAssured.baseURI="http://216.10.245.166";
        Response res = given().
                queryParam("key","qaclick123")
                .body(bodyPost)
                .when()
                .post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).and()
                .contentType(ContentType.JSON).and()
                .body("status",equalTo("OK"))
                .extract().response();
        // Task 1: get the response to a string
        String responseStr = res.asString();
        System.out.println(responseStr);
        // Task 2: convert the string to json
        JsonPath js = new JsonPath(responseStr);
        //Task 3: extract placeId from the response
        String placeId = js.get("place_id");
        System.out.println(placeId);
        String bodyDelete="{\n" +
                "\"place_id\":\""+placeId+"\"\n" +
                "}";

        Response res1 = given().
                queryParam("key","qaclick123")
                .body(bodyDelete)
                .when()
                .post("/maps/api/place/delete/json")
                .then().assertThat().statusCode(200).and()
                .body("status",equalTo("OK"))
                .extract().response();
        String delResponse = res1.asString();
        JsonPath json1 = new JsonPath(delResponse);
        String delStatus = json1.getString("status");
        System.out.println(delStatus);

    }
}
