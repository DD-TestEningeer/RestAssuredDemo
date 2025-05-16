package users;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JSONBodyTest {

	@Test(priority = 1)

	public void getSingleUser() {

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		// base url

		RestAssured.baseURI = "https://reqres.in/api";

		Response response = RestAssured

				.given()

				.headers("x-api-key", "reqres-free-v1")

				.when().get("/users/2");

		System.out.println(response.asString());

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

	}

	@Test(priority = 1)

	public void getSingleUser2() {

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		// base url

		RestAssured.baseURI = "https://reqres.in/api";

		Response response = RestAssured

				.given()

				.headers("x-api-key", "reqres-free-v1")

				.when().get("/users/2");

		System.out.println(response.asString());

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

	}

	@Test(priority = 3)

	public void postCreateUser() {

//	 RestAssured.post("https://reqres.in/api/users");

		// given() - headers, authorization, body, params
		// when() - get/post/put/patch/delete
		// then() - assertions/validation

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		// using string variable => small/simples request body

		String jsonBody = "{\r\n" + "  \"name\": \"morpheus\",\r\n" + "  \"job\": \"leader\"\r\n" + "}";

		Response response = RestAssured

				.given()

				.body(jsonBody).headers("x-api-key", "reqres-free-v1")

				.when().post("https://reqres.in/api/users");

		System.out.println(response.asString());

		Assert.assertEquals(response.getStatusCode(), 201);

//		System.out.println(response.asPrettyString());

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

	}

	@Test(priority = 3)

	public void postCreateUser2() {

//	 RestAssured.post("https://reqres.in/api/users");

		// given() - headers, authorization, body, params
		// when() - get/post/put/patch/delete
		// then() - assertions/validation

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		// using HashMap

//		String jsonBody = "{\r\n" + "  \"name\": \"morpheus\",\r\n" + "  \"job\": \"leader\"\r\n" + "}";

		// complex body data or vary the data

		HashMap<String, String> map = new HashMap<>();

		map.put("name", "demouser");
		map.put("job", "leader");

		Response response = RestAssured

				.given()

				.body(map).headers("x-api-key", "reqres-free-v1")

				.when().post("https://reqres.in/api/users");

		System.out.println(response.asString());

		Assert.assertEquals(response.getStatusCode(), 201);

//		System.out.println(response.asPrettyString());

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

	}

	@Test(priority = 3)

	public void postCreateUser3() {

//	 RestAssured.post("https://reqres.in/api/users");

		// given() - headers, authorization, body, params
		// when() - get/post/put/patch/delete
		// then() - assertions/validation

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		// using POJO => Plain Old Java Object

		// complex body data or vary the data

//		HashMap<String, String> map = new HashMap<>();		
//		map.put("name", "demouser");
//		map.put(  "job", "leader");

		UserPojoClass obj = new UserPojoClass();

		obj.setName("demouser");
		obj.setJob("QA");

		Response response = RestAssured

				.given()

				.body(obj.toString()).headers("x-api-key", "reqres-free-v1")

				.when().post("https://reqres.in/api/users");

		System.out.println(response.asString());

		Assert.assertEquals(response.getStatusCode(), 201);

//		System.out.println(response.asPrettyString());

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

	}

	@Test(priority = 3)

	public void postCreateUser4() {

//	 RestAssured.post("https://reqres.in/api/users");

		// given() - headers, authorization, body, params
		// when() - get/post/put/patch/delete
		// then() - assertions/validation

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		// using POJO => Plain Old Java Object

		// complex body data or vary the data

//		HashMap<String, String> map = new HashMap<>();		
//		map.put("name", "demouser");
//		map.put(  "job", "leader");

		UserPojoClass obj = new UserPojoClass();

		obj.setName("demouser2");
		obj.setJob("Lead");

		Response response = RestAssured

				.given()

				.body(obj.toString()).headers("x-api-key", "reqres-free-v1")

				.when().post("https://reqres.in/api/users");

		System.out.println(response.asString());

		Assert.assertEquals(response.getStatusCode(), 201);

//		System.out.println(response.asPrettyString());

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

	}

}
