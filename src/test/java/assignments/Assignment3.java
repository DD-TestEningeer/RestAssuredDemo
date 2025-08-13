package assignments;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Assignment3 {
	
	
	// GitHub Token

    String token = "ghp_yourGeneratedTokenHere"; // Replace with your actual token

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api.github.com";
    }

    @Test
    public void verifyOAuthAuthentication() {
        // Send GET request with OAuth token
        Response response = RestAssured
                .given()
                    .auth()
                    .oauth2(token)
                    .contentType(ContentType.JSON)
                .when()
                    .get("/user");

        // Log the response
//        response.then().log().all();
        System.out.println(response.asPrettyString());

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch");
        Assert.assertNotNull(response.jsonPath().getString("login"), "Login should not be null");
        Assert.assertTrue(response.jsonPath().getInt("id") > 0, "ID should be greater than 0");
    }
}

