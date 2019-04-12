import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class BasicsGet
{
    @Test
    public void restGetRequest()
    {
        RestAssured.baseURI="https://maps.googleapis.com";
        given()
                .param("key","xxxxxxxxxpppppxxxxxxxxxx") // enter actual key here
                .param("input","Art Museum India")
                .param("inputtype","textquery")
                .param("fields","formatted_address,geometry,icon,id,name,photos,user_ratings_total")
                .when()
                .get("/maps/api/place/findplacefromtext/json")
                .then().assertThat().statusCode(200).and()
                .contentType(ContentType.JSON).and()
                .body("candidates[0].name",equalTo("National Gallery of Modern Art")).and()
                .body("candidates[0].id",equalTo("f49bdcfa5ef1fb687d202e1367e7a4225f159ae1")).and()
                .header("Server","scaffolding on HTTPServer2");
    }

}
