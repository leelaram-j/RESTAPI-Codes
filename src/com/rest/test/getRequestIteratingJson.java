package com.rest.test;

import com.reusable.methods.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class getRequestIteratingJson
{
    @Test
    public void restGetRequest()
    {
        //This test logs both requests and response....but in real time some logging are used as it will consume space.
        RestAssured.baseURI="https://maps.googleapis.com";
        Response res =  given()
                .param("key","xxxxxxxxppppppppxpxpxpxpxpxpx") // enter actual key here
                .param("radius","500")
                .param("location","-33.8670522,151.1957362")
                .log().all()
                .when()
                .get("/maps/api/place/nearbysearch/json")
                .then().log().all()
                .assertThat().statusCode(200).and()
                .contentType(ContentType.JSON)
                .extract().response();
        JsonPath jp = ReusableMethods.toJson(res);
        int count = jp.get("results.size()");
        //System.out.println(count);
        for(int i=0;i<count;i++)
        {
            String placeId = jp.get("results["+i+"].place_id");
            System.out.println(placeId);
        }

    }
}
