package users;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import tests2.Logger;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.logging.log4j.LogManager;

public class GetUserTest2 {
	
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);
	
	@Test
	
	public void getUserDetails1() {
		
		
		Response response = RestAssured.given()

					.when().get("https://reqres.in/api/users?page=2");

//		System.out.println(response.asString()); // printing the response data as a string
		
		   logger.info(response.asString());
		   
		   try {
			   
			   System.out.println(2/0);
			
		} catch (Exception e) {

			logger.error(e.getMessage());
		}
		
		
	}

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
					.queryParam("page", 4)
					.when().get("https://reqres.in/api/users");

		

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

	}

}
