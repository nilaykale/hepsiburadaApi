import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ApiTestCases {

    @Test
    void CheckAllGrocery() {

        Response response = get("https://a19b4e32-1267-44ab-a421-186ff6b27cd5.mock.pstmn.io/allGrocery");

        System.out.println(response.asString());
        System.out.println(response.getBody());
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        System.out.println((response.getTime()));

        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 200);
    }

    @Test
    void CheckAllGroceryData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        AllGroceryResult expected = mapper.readValue(new File("src/data/AllProducts.json"), AllGroceryResult.class);

        AllGroceryResult response = given()
                .when()
                .get("https://a19b4e32-1267-44ab-a421-186ff6b27cd5.mock.pstmn.io/allGrocery")
                .as(AllGroceryResult.class);

        Assert.assertEquals(expected.data.size(), response.data.size(), "Actual item number and response's item number are not the same.");
        for (int i = 0; i < expected.data.size(); i++) {
            JsonKeys expectedItem = expected.data.get(i);
            JsonKeys actualItem = response.data.get(i);

            Assert.assertEquals(expectedItem.getId(), actualItem.getId(), "Id number are not equal");
            Assert.assertEquals(expectedItem.getName(), actualItem.getName(), "Item names are not equal");
            Assert.assertEquals(expectedItem.getStock(), actualItem.getStock(), "Stock numbers are not equal");
            Assert.assertEquals(expectedItem.getPrice(), actualItem.getPrice(), "Prices are not equal");
        }
    }

    @Test
    void CheckAppleData() throws IOException {
            ObjectMapper mapper = new ObjectMapper();

            AllGroceryResult expected = mapper.readValue(new File("src/data/Apple.json"), AllGroceryResult.class);

            AllGroceryResult response = given()
                    .when()
                    .get("https://a19b4e32-1267-44ab-a421-186ff6b27cd5.mock.pstmn.io/allGrocery/apple")
                    .as(AllGroceryResult.class);

            Assert.assertEquals(expected.data.size(), response.data.size(), "Actual item number and response's item number are not the same.");
            for (int i = 0; i < expected.data.size(); i++) {
                JsonKeys expectedItem = expected.data.get(i);
                JsonKeys actualItem = response.data.get(i);

                Assert.assertEquals(expectedItem.getId(), actualItem.getId(), "Id number are not equal");
                Assert.assertEquals(expectedItem.getName(), actualItem.getName(), "Item names are not equal");
                Assert.assertEquals(expectedItem.getStock(), actualItem.getStock(), "Stock numbers are not equal");
                Assert.assertEquals(expectedItem.getPrice(), actualItem.getPrice(), "Prices are not equal");
            }
        }

    @Test
    void CheckGrapesData()throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        AllGroceryResult expected = mapper.readValue(new File("src/data/Grapes.json"), AllGroceryResult.class);

        AllGroceryResult response = given()
                .when()
                .get("https://a19b4e32-1267-44ab-a421-186ff6b27cd5.mock.pstmn.io/allGrocery/grapes")
                .as(AllGroceryResult.class);

        Assert.assertEquals(expected.data.size(), response.data.size(), "Actual item number and response's item number are not the same.");
        for (int i = 0; i < expected.data.size(); i++) {
            JsonKeys expectedItem = expected.data.get(i);
            JsonKeys actualItem = response.data.get(i);

            Assert.assertEquals(expectedItem.getId(), actualItem.getId(), "Id number are not equal");
            Assert.assertEquals(expectedItem.getName(), actualItem.getName(), "Item names are not equal");
            Assert.assertEquals(expectedItem.getStock(), actualItem.getStock(), "Stock numbers are not equal");
            Assert.assertEquals(expectedItem.getPrice(), actualItem.getPrice(), "Prices are not equal");
        }
    }

    @Test
    void CheckNotFound() {
        given()
                .get("https://a19b4e32-1267-44ab-a421-186ff6b27cd5.mock.pstmn.io/allGrocery/pierce")
                .then()
                .statusCode(404);
    }

    @Test
    void CheckBadRequest() {
        given()
                .get("https://a19b4e32-1267-44ab-a421-186ff6b27cd5.mock.pstmn.io/allGrocery/strawberry")
                .then()
                .statusCode(400);
    }

    @Test
    void CheckPost() {
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
