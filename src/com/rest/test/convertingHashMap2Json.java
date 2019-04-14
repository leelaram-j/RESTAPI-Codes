package com.rest.test;

import com.reusable.methods.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class convertingHashMap2Json
{
    @Test
    public void convertHashMaptoJson()
    {
        HashMap<String,Object> hp = new HashMap<>();
        hp.put("name","Learn Appium Automation with Java");
        hp.put("isbn","tango");
        hp.put("aisle","227");
        hp.put("author","John Doe");

        /* when we have nested json structure use this
        * HashMap<String,Object> hp1 = new HashMap<>();
        * hp1.put("dfsdfsd","dsfsd");
        * hp1.put("sjhdsajd","dgsjh");
        * hp.put("location",hp1); /// this way the entire nested structure goes inside the parent json body.
        * */

        RestAssured.baseURI="http://216.10.245.166";
        Response res = given().
                header("Content-Type","application/json").
                body(hp)
                .when()
                .post("/Library/Addbook.php")
                .then().assertThat().statusCode(200).and()
                .contentType(ContentType.JSON).and()
                .extract().response();
        JsonPath js = ReusableMethods.toJson(res);
        String s = js.get("ID");
        System.out.println(s);

    }
}
