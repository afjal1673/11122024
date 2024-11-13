package testPackage;

import java.util.HashMap;
import java.util.Random;

import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class ApitestA {
	
	int id;
	String username;
	String email;
	
	@BeforeTest
	public void setup() {
		// Generate random number
		id = new Random().nextInt(10000);
		username = "testUser"+id;
		email = "Test_Email"+id+"@gmail.com";
		
		// set base URL
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		// print generated values
		System.out.println("Generated ID:"+id);
		System.out.println("Generated Username:"+username);
		System.out.println("Generated Email:"+email);
		
	}
	
	//public static Response response;
	// post Request 
	@Test(priority =1)
	public   void postRequest() {
		
		//HashMap<String,String> reqBody = new HashMap<String,String>();
		JSONObject reqBody = new JSONObject();
		reqBody.put("id", id);
		reqBody.put("username",username);
		reqBody.put("firstname", "Tom");
		reqBody.put("lastname", "tomy");
		reqBody.put("email", email);
		reqBody.put("password", "12345");
		reqBody.put("phone", "1234567890");
		
	Response response= RestAssured
			.given()
			.contentType(ContentType.JSON)
			.body(reqBody.toString())
			.when()
			.post("/user");
	
	// print response
	System.out.println("Post Response Code: "+response.getStatusCode());
	System.out.println("Post Response Code: "+response.getBody().asString());
	
	// validate the response code
	Assert.assertEquals(response.getStatusCode(), 200);
	
	// Validate response time( should be less than 5000 milliseconds)
	long responseTime = response.getTime();
	System.out.println("Response Time: "+ responseTime+ " ms");
	
		
	}
	
	// Get Request
	@Test (priority =2)
	public void testGetUser() {
	Response response = RestAssured
			.given()
			.pathParam("username",username)
			.when()
			.get("/user/{username}");
		
		// print response
		System.out.println("Get Response Code: "+response.getStatusCode());
		System.out.println("Get Response Body: "+response.getBody().asString());
		
		// validate the response 
		Assert.assertEquals(response.getStatusCode(),200 );
	}
	@Test (priority =3)
	public void putRequest() {
		JSONObject object = new JSONObject();
		object.put("id", id);
		object.put("username", username);
		object.put("firstname", "Tom");
		object.put("lastname", "Tomy");
		object.put("email", email);
		object.put("password", "12345");
		object.put("phone", "1234567890");
		
		Response response = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.pathParam("username", username)
				.body(object.toString())
				.when()
				.put("/user/{username}");
		
		System.out.println("Put response code :" + response.getStatusCode());
		System.out.println("Put response body :" + response.getBody().asString());
		//System.out.println("Put response code :" + response.getStatusCode());
	}
	@Test(priority =4)
	public void putGetUser() {
		Response response = RestAssured
				.given()
				.pathParam("username",username)
				.when()
				.get("/user/{username}");
			
			// print response
			System.out.println("Get Response Code: "+response.getStatusCode());
			System.out.println("Get Response Body: "+response.getBody().asString());
			
			// validate the response 
			Assert.assertEquals(response.getStatusCode(),200 );
	
	}
	@Test(priority =5)
	public void deleteRequest() {
		Response response = RestAssured
				.given()
				.pathParam("username", username)
				.when()
				.delete("/user/{username}");
		
		System.out.println("Delete Response Code: "+response.getStatusCode());
		System.out.println("Delete Response Body: "+response.getBody().asString());
		
		// validate the response 
					Assert.assertEquals(response.getStatusCode(),200 );
	}
	@Test(priority =6)
	public void deleteGetUser() {
		Response response = RestAssured
				.given()
				.pathParam("username",username)
				.when()
				.get("/user/{username}");
			
			// print response
			System.out.println("Get Response Code: "+response.getStatusCode());
			System.out.println("Get Response Body: "+response.getBody().asString());
			
			// validate the response 
			Assert.assertEquals(response.getStatusCode(),404 );
	
	
	}
	
}