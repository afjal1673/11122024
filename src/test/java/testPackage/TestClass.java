
package testPackage;

import java.util.HashMap;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestClass {

	@Test
	public static void postRequest() {

		HashMap<String, String> reqbody = new HashMap<String, String>();
		reqbody.put("username", "rony");
		reqbody.put("frirstName", "monir");
		reqbody.put("lastName", "arman");
		reqbody.put("email", "almohid@gmail.com");
		reqbody.put("password", "1202556");
		reqbody.put("phone", "9162501201");

		given().contentType("application/json").body(reqbody).when().post("https://petstore.swagger.io/v2/user").then()
				.statusCode(200).log().all();
	}

	@Test
	public static void getRequest() {
		given().contentType("application/json").when().get("https://petstore.swagger.io/v2/user/rony").then()
				.statusCode(200).log().all();
	}

	@Test
	public static void putRequest() {

		HashMap<String, String> reqbody = new HashMap<String, String>();
		reqbody.put("username", "rony");
		reqbody.put("frirstName", "monir1");
		reqbody.put("lastName", "arman");
		reqbody.put("email", "almohid@gmail.com");
		reqbody.put("password", "1202556");
		reqbody.put("phone", "9162501201");

		given().contentType("application/json").body(reqbody).when().put("https://petstore.swagger.io/v2/user/rony")
				.then().statusCode(200).log().all();
	}

	@Test
	public static void getRequest1() {
		given().contentType("application/json").when().get("https://petstore.swagger.io/v2/user/rony").then()
				.statusCode(200).log().all();
	}

	@Test
	public static void deletRequest() {
		given().contentType("application/json").when().get("https://petstore.swagger.io/v2/user/rony").then()
				.statusCode(200).log().all();
	}

	@Test
	public static void getRequest2() {
		given().contentType("application/json").when().get("https://petstore.swagger.io/v2/user/rony").then()
				.statusCode(200).log().all();
	}

}// class
