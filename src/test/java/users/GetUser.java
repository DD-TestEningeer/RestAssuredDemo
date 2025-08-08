package users;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetUser {

	@Test(priority = 1)

	public void getListUsers() {

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		// RestAssured Library use for sending get request

		// URL - https://reqres.in/api/users?page=2

		Response response = RestAssured.given()

				.headers("x-api-key", "reqres-free-v1")

				.when().get("https://reqres.in/api/users?page=2");

		System.out.println(response.asString()); // printing the response data as a string

		System.out.println(response.asPrettyString()); // print the response data as a pretty string Json

		Assert.assertEquals(response.getStatusCode(), 200); // assertion for the status code

		// response what details we can check/validate ?

		System.out.println(response.getStatusLine());
		System.out.println(response.getContentType());

		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8"); // content type validation

		System.out.println("response body :" + response.getBody().asString());

		String actData = response.getBody().asString();

		Assert.assertEquals(actData.contains("page"), true); // validating the json body

		System.out.println(response.getTimeIn(TimeUnit.MILLISECONDS));

		Assert.assertEquals(response.getTimeIn(TimeUnit.MILLISECONDS), 1000); // assesrtion for the response time

		System.out.println(response.getHeaders());

		Assert.assertEquals(response.getStatusCode(), 200);

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

	}
	
	
	
	
	
	
	
	
	
	

	@Test(priority = 2)

	public void getSingleUser() {

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		Response response = RestAssured

				.given()

				.headers("x-api-key", "reqres-free-v1")

				.when().get("https://reqres.in/api/users/2");

		System.out.println(response.asString());
		System.out.println(response.asPrettyString());

//		System.out.println(response.asPrettyString());

		Assert.assertEquals(response.getStatusCode(), 200);

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

	}

	@Test(priority = 3)

	public void postCreateUser() {

//	 RestAssured.post("https://reqres.in/api/users");

		// given() - headers, authorization, body, params
		// when() - get/post/put/patch/delete - url or endpoint 
		// then() - assertions/validation

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		
		String jsonData = "{\r\n" + "    \"name\": \"dnyaneshwar d\",\r\n" + "    \"job\": \"QA\"\r\n" + "}";

		Response response = RestAssured

				.given().body(jsonData).headers("x-api-key", "reqres-free-v1")

				.when().post("https://reqres.in/api/users");
		
		
		

		System.out.println(response.asString());

		Assert.assertEquals(response.getStatusCode(), 201);

//		System.out.println(response.asPrettyString());

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

	}

	@Test(priority = 4)

	public void putFullDataUpdate() {

		String jsonBody = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";

//		RestAssured

		Response response = RestAssured.given()

				// given()
				.body(jsonBody).headers("x-api-key", "reqres-free-v1")

				// when()
				.when().put("https://reqres.in/api/users/2");

		System.out.println("The response is : " + response.asString());
//		System.out.println("The response in Json format : " + response.asPrettyString());

		Assert.assertEquals(response.getStatusCode(), 200);

		// then()

	}
	
	
	
	
	

	@Test(priority = 5)

	public void putFullDataUpdate2() {

		String jsonBody = "{\r\n" + "  \"id\": 0,\r\n" + "  \"category\": {\r\n" + "    \"id\": 0,\r\n"
				+ "    \"name\": \"string\"\r\n" + "  },\r\n" + "  \"name\": \"doggie\",\r\n" + "  \"photoUrls\": [\r\n"
				+ "    \"string\"\r\n" + "  ],\r\n" + "  \"tags\": [\r\n" + "    {\r\n" + "      \"id\": 0,\r\n"
				+ "      \"name\": \"string\"\r\n" + "    }\r\n" + "  ],\r\n" + "  \"status\": \"available\"\r\n" + "}";
//		RestAssured

		Response response = RestAssured.given()

				// given()
				.body(jsonBody).headers("accept", "application/json").headers("Content-Type", "application/json")

				// when()
				.when().put("https://petstore.swagger.io/v2/pet");

		System.out.println("The response is : " + response.asString());
//		System.out.println("The response in Json format : " + response.asPrettyString());

		Assert.assertEquals(response.getStatusCode(), 200);

		// then()

	}

	@Test(priority = 6)

	public void patchParitalDataUpdate() {

		// given
		Response response = RestAssured.given().body("{\r\n" + "    \"name\": \"testUser\"\r\n" + "}")
				.headers("x-api-key", "reqres-free-v1")

				// when
				.when().patch("https://reqres.in/api/users/2");
		// then

		System.out.println(response.asPrettyString());
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 7)

	public void deleteUser() {

		// given()
		Response response = RestAssured.given()

				.headers("x-api-key", "reqres-free-v1")

				// when()

				.when().delete("https://reqres.in/api/users/2");
		// then()

		System.out.println("The delete request response : " + response.asString());
		Assert.assertEquals(response.getStatusCode(), 204);

	}

	@Test(priority = 8)

	public void getSingleUser2() {

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		RestAssured

				.given()

				.headers("x-api-key", "reqres-free-v1")

				.when().get("https://reqres.in/api/users/2")

//				.then().log().all(); // printing all the data to console

				.then().statusCode(200);

	}

}
