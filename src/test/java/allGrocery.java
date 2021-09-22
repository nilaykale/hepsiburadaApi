import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.matcher.ResponseAwareMatcher.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class allGrocery {

    @Test
    void allGrocery(){

        Response response=get("https://a19b4e32-1267-44ab-a421-186ff6b27cd5.mock.pstmn.io/allGrocery");

        System.out.println(response.asString());
        System.out.println(response.getBody());
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        System.out.println((response.getTime()));

        int statusCode =response.getStatusCode();

        Assert.assertEquals(statusCode,200);
    }
    @Test
    void allGrocery2(){
        given()
                .get("https://a19b4e32-1267-44ab-a421-186ff6b27cd5.mock.pstmn.io/allGrocery")
        .then()
                .statusCode(200)
                .log().all()
                .body("data[0]['id']", equalTo(1));
    }

    @Test
    void notFound(){
        given()
                .get("https://a19b4e32-1267-44ab-a421-186ff6b27cd5.mock.pstmn.io/allGrocery/pierce")
        .then()
                .statusCode(404);
    }

    @Test
    void badRequest(){
        given()
                .get("https://a19b4e32-1267-44ab-a421-186ff6b27cd5.mock.pstmn.io/allGrocery/strawberry")
                .then()
                .statusCode(400);
    }

    @Test
    void post(){
        given().
                body("{\n" +
                        " \"id\": 4,\n" +
                        " \"name\": \"cucumber\",\n" +
                        " \"price\": 12.3,\n" +
                        " \"stock\": 3\n" +
                        "}").
                when().
                post("https://a19b4e32-1267-44ab-a421-186ff6b27cd5.mock.pstmn.io/allGrocery/add").
                then().
                statusCode(200);
    }
}
