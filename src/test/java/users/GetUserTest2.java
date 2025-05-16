package users;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class GetUserTest2 {

	@Test
	
	public void getUserDetails() {
		
		given()
		.when().get("https://reqres.in/api/users/2")
		.then().log().all();
	}
	
	
	@Test (priority = 1)

	public void getListUsers() {

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		// RestAssured Library use for sending get request

		// URL - https://reqres.in/api/users?page=2
		
		

		Response response = RestAssured.given()
		
					.headers("x-api-key", "reqres-free-v1")
					.queryParam("page", 2)
					.queryParam("page", 3)
					.when().get("https://reqres.in/api/users");

		

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

	}

}
