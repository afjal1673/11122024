
package testPackage;
import java.util.Random;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class APT_AD {
//test update
    private int id;
    private String username;
    private String email;

    @BeforeTest
    public void setup() {
        // Generate random ID and user details
        id = new Random().nextInt(10000);
        username = "testUser" + id;
        email = "Test_Email" + id + "@gmail.com";

        // Set base URI for API requests
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        // Log generated values
        System.out.printf("Generated ID: %d%n", id);
        System.out.printf("Generated Username: %s%n", username);
        System.out.printf("Generated Email: %s%n", email);
    }

    // Helper method to create a JSON body for user-related requests
    private JSONObject createUserJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("username", username);
        json.put("firstname", "Tom");
        json.put("lastname", "Tomy");
        json.put("email", email);
        json.put("password", "12345");
        json.put("phone", "1234567890");
        return json;
    }

    @Test(priority = 1)
    public void postRequest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(createUserJson().toString())
                .when()
                .post("/user");

        // Log and validate response
        System.out.printf("Post Response Code: %d%n", response.getStatusCode());
        System.out.println("Post Response Body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);

        // Validate response time
        long responseTime = response.getTime();
        System.out.printf("Response Time: %d ms%n", responseTime);
    }

    @Test(priority = 2)
    public void getUser() {
        Response response = given()
                .pathParam("username", username)
                .when()
                .get("/user/{username}");

        // Log and validate response
        System.out.printf("Get Response Code: %d%n", response.getStatusCode());
        System.out.println("Get Response Body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void putRequest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("username", username)
                .body(createUserJson().toString())
                .when()
                .put("/user/{username}");

        // Log and validate response
        System.out.printf("Put Response Code: %d%n", response.getStatusCode());
        System.out.println("Put Response Body: " + response.getBody().asString());
    }

    @Test(priority = 4)
    public void getUserAfterPut() {
        getUser();  // Reuse existing logic for GET request
    }

    @Test(priority = 5)
    public void deleteRequest() {
        Response response = given()
                .pathParam("username", username)
                .when()
                .delete("/user/{username}");

        // Log and validate response
        System.out.printf("Delete Response Code: %d%n", response.getStatusCode());
        System.out.println("Delete Response Body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 6)
    public void getUserAfterDelete() {
        Response response = given()
                .pathParam("username", username)
                .when()
                .get("/user/{username}");

        // Log and validate response
        System.out.printf("Get Response Code: %d%n", response.getStatusCode());
        System.out.println("Get Response Body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 404);
    }
}
