package assignments;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Assignment1 {

//	Assignment on API Testing
//	Q.1. Find the endpoints and test with Postman using chai assertion 
//	and automate same in RESTAssured.
	
	int petID ;	

	@Test (priority = 1)

	public void findPetByStatus() {

		Response response = RestAssured
	            .given()
	            .header("accept", "application/json")
	            .when()
	            .get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");
		
		// Print entire response to the console
		System.out.println("The response is : " + response.asString());

		// Assert status code is 200
		int statusCode = response.getStatusCode();
		System.out.println("The status code is : " + statusCode);
		Assert.assertEquals(statusCode, 200);

		// Extract the status of the first object in the response array
		String petStatus = response.jsonPath().getString("[0].status");
		System.out.println("The status is : " + petStatus);
		Assert.assertEquals(petStatus, "available");

	}
	
	@Test(priority = 2)
	public void addNewPetToStore() {
		
		
		//2. Add new Pet to the store
		
		
		String jsonBody = "{\"id\":222,\"category\":{\"id\":22,\"name\":\"cat\"},\"name\":\"cat\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"cat\"}],\"status\":\"available\"}";
		
		
		Response response = RestAssured 
							
							.given()
							.header("accept","application/json")
							.header("Content-Type","application/json")
							.body(jsonBody)
							.when()
							.post("https://petstore.swagger.io/v2/pet");
		
		System.out.println("The response is : " + response.asString());
//		System.out.println("The response is : " + response.asPrettyString());

		// Validate the status code from the response body
		int statusCode = response.getStatusCode();
		System.out.println("The status code is : " + statusCode);
		Assert.assertEquals(statusCode, 200);
		
		// Validate the content type from the response body		
		String contentType = response.getContentType();
		System.out.println("The contentType is : " + contentType);
		Assert.assertEquals(contentType, "application/json");
		
		// Validate the pet id from the response body
		petID = response.jsonPath().getInt("id");
		System.out.println("The pet id is : " + petID);
		Assert.assertEquals(petID, 222);

		// Validate the pet name from the response body
		String petName = response.jsonPath().getString("category.name");
		System.out.println("The pet name is : " + petName);
		Assert.assertEquals(petName, "cat");

	}
	
	
	@Test (priority = 3, dependsOnMethods = "addNewPetToStore")
	
	public void getPetDetails() {
		
//		3. For the same generated id find the pet Curl
		
		
		Response response = RestAssured
				
							.given()
							.header("accept","application/json")
							.when()
							.get("https://petstore.swagger.io/v2/pet/"+petID);
		
		
		System.out.println("The response is : " + response.asString());
		
		// Validate the status code from the response body
		int statusCode = response.getStatusCode();
		System.out.println("The status code is : " + statusCode);
		Assert.assertEquals(statusCode, 200);
		
	}
	
	
	
	@Test (priority = 4)
	public void updatePetDetails() {
		
		//4. Update an existing pet URL=> https://petstore.swagger.io/v2/pet
		
		String url = "https://petstore.swagger.io/v2/pet";
		
		String jsonBody = "{\"id\":300,\"category\":{\"id\":33,\"name\":\"parrot\"},\"name\":\"parrot\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";
		
		Response response = RestAssured
							.given()
							.header("accept","application/json")
							.header("Content-Type","application/json")
							.body(jsonBody)
							.when()
							.put(url);

		System.out.println("The response is : " + response.asString());
		
		// Validate the status code from the response body
		int statusCode = response.getStatusCode();
		System.out.println("The status code is : " + statusCode);
		Assert.assertEquals(statusCode, 200);
		
		
	}
	

//5. Delete existing pet with same id
	
	@Test (priority = 5, dependsOnMethods = "addNewPetToStore")
	public void deletePetWithSameID() {
		
		String url = "https://petstore.swagger.io/v2/pet/" + petID; 
		
		Response response = RestAssured
							.given()
							.header("accept","application/json")
							.when()
							.delete(url);
		
		System.out.println("The response is : " + response.asString());

		// Validate the status code from the response body
		int statusCode = response.getStatusCode();
		System.out.println("The status code is : " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	
	
	
	
	
	
	
	
	@BeforeMethod
	public void setUp() {

		testStarted();
	}

	@AfterMethod
	public void tearDown() {

		testEnded();
	}
	
	
	
	public static void testStarted() {
		System.out.println("++++++++++++++++++ Test Started ++++++++++++++++++");
		System.out.println("--------------------------------------------------");
	}

	public static void testEnded() {
		System.out.println("--------------------------------------------------");
		System.out.println("+++++++++++++++++++ Test Ended +++++++++++++++++++");
		System.out.println();

	}
	
	

}
