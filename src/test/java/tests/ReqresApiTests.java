package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ReqresApiTests {

	String userId; // to store the user ID created via POST

	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://reqres.in/api";
	}

	@Test
	public void testGetUsers() {
		// GET request to fetch user list
		given().when().get("/users?page=2").then().statusCode(200). // Validate HTTP status
				body("data", not(empty())); // Ensure data array is not empty
	}

	@Test
	public void testCreateUser() {
		// Create a new user with POST
		Map<String, String> user = new HashMap<>();
		user.put("name", "John");
		user.put("job", "Tester");

		Response response = given().contentType("application/json").body(user).header("x-api-key", "reqres-free-v1")
				
				
				.when().post("/users").then()
				.statusCode(201). // Created
				body("name", equalTo("John")).extract().response();

		userId = response.jsonPath().getString("id"); // Capture generated user ID for later tests
	}

	@Test(dependsOnMethods = { "testCreateUser" })
	public void testUpdateUserWithPut() {
		// Full update using PUT
		Map<String, String> update = new HashMap<>();
		update.put("name", "John");
		update.put("job", "Lead QA");

		given().contentType("application/json").body(update).header("x-api-key", "reqres-free-v1")
		.when().put("/users/" + userId).then().statusCode(200). // OK
				body("job", equalTo("Lead QA")); // Validate updated job title
	}

	@Test(dependsOnMethods = { "testCreateUser" })
	public void testUpdateUserWithPatch() {
		// Partial update using PATCH
		Map<String, String> patchData = new HashMap<>();
		patchData.put("job", "QA Architect");

		given().contentType("application/json").body(patchData).header("x-api-key", "reqres-free-v1")
		.when().patch("/users/" + userId).then().statusCode(200)
				.body("job", equalTo("QA Architect")); // Validate updated job
	}

	@Test(dependsOnMethods = { "testCreateUser" })
	public void testDeleteUser() {
		// DELETE request to remove the user
		given().header("x-api-key", "reqres-free-v1").
		when().delete("/users/" + userId).then().statusCode(204); // No Content indicates success
	}
}
