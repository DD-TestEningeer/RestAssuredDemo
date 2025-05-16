package tests;

import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FullCrudTest {
	String userId;

	@Test(priority = 1)
	public void createUser() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("name", "David");
		data.put("job", "Analyst");
		Response response = given().baseUri("https://reqres.in").contentType(ContentType.JSON).body(data).when()
				.post("/api/users");
		userId = response.jsonPath().getString("id");
	}

	@Test(priority = 2 , dependsOnMethods = "createUser")
	public void updateUser() {
		Map<String, String> update = new HashMap<>();
		update.put("name", "David Updated");
		update.put("job", "Manager");

		given().baseUri("https://reqres.in").header("x-api-key", "reqres-free-v1").contentType(ContentType.JSON)
				.body(update).when().put("/api/users/" + userId).then().statusCode(200);
	}

	@Test(priority = 2 , dependsOnMethods = "updateUser")
	public void deleteUser() {
		given().baseUri("https://reqres.in").header("x-api-key", "reqres-free-v1").when().delete("/api/users/" + userId).then().statusCode(204);
	}
}
