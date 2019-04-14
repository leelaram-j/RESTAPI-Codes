package com.rest.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class postXMLDemo
{
    @Test
    public void postXMLDemo() throws IOException {
        String postData = GenerateStringFromResource("//Users//lee//Documents//RestAssuredDemo//Resources//com.rest.test.postXMLDemo.xml");
        System.out.println(postData);
        RestAssured.baseURI="http://216.10.245.166";
        Response res = given().
                queryParam("key","qaclick123")
                .body(postData)
                .when()
                .post("/maps/api/place/add/xml")
                .then().assertThat().statusCode(200).and().contentType(ContentType.XML)
                .extract().response();
        String response1 = res.asString();
        System.out.println(response1);
        XmlPath xmlPath = new XmlPath(response1);
        String xmlResponse = xmlPath.get("response.place_id");
        System.out.println(xmlResponse);

    }
    public static String GenerateStringFromResource(String path) throws IOException
    {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
