package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestfulBookerTests {

    String token;
    int bookingId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        // Create Auth Token
        Map<String, String> creds = new HashMap<>();
        creds.put("username", "admin");
        creds.put("password", "password123");

        Response response = given()
                                .contentType("application/json")
                                .body(creds)
                            .when()
                                .post("/auth")
                            .then()
                                .statusCode(200)
                                .extract().response();

        token = response.jsonPath().getString("token");
        
        System.out.println("The token value : " + token);
    }
    
    
    

    @Test (priority = 1)
    public void testCreateBooking() {
        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2023-10-01");
        bookingDates.put("checkout", "2023-10-10");

        Map<String, Object> booking = new HashMap<>();
        booking.put("firstname", "John");
        booking.put("lastname", "Doe");
        booking.put("totalprice", 120);
        booking.put("depositpaid", true);
        booking.put("bookingdates", bookingDates);
        booking.put("additionalneeds", "Breakfast");

        Response response = given()
                                .contentType("application/json")
                                .body(booking)
                            .when()
                                .post("/booking")
                            .then()
                                .statusCode(200)
                                .body("booking.firstname", equalTo("John"))
                                .extract().response();

        bookingId = response.jsonPath().getInt("bookingid");
    }

    @Test(priority = 2 , dependsOnMethods = "testCreateBooking")
    public void testGetBookingById() {
        given()
        .when()
            .get("/booking/" + bookingId)
        .then()
            .statusCode(200)
            .body("firstname", equalTo("John"));
    }

    @Test(priority = 3 ,dependsOnMethods = "testCreateBooking")
    public void testUpdateBookingWithPut() {
        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2023-11-01");
        bookingDates.put("checkout", "2023-11-10");

        Map<String, Object> updatedBooking = new HashMap<>();
        updatedBooking.put("firstname", "Jane");
        updatedBooking.put("lastname", "Smith");
        updatedBooking.put("totalprice", 150);
        updatedBooking.put("depositpaid", false);
        updatedBooking.put("bookingdates", bookingDates);
        updatedBooking.put("additionalneeds", "Lunch");

        given()
            .contentType("application/json")
            .header("Cookie", "token=" + token)
            .body(updatedBooking)
        .when()
            .put("/booking/" + bookingId)
        .then()
            .statusCode(200)
            .body("firstname", equalTo("Jane"))
            .body("totalprice", equalTo(150));
    }

    @Test(priority = 4 ,dependsOnMethods = "testCreateBooking")
    public void testPartialUpdateWithPatch() {
        Map<String, Object> patchData = new HashMap<>();
        patchData.put("firstname", "Alice");
        patchData.put("additionalneeds", "Dinner");

        given()
            .contentType("application/json")
            .header("Cookie", "token=" + token)
            .body(patchData)
        .when()
            .patch("/booking/" + bookingId)
        .then()
            .statusCode(200)
            .body("firstname", equalTo("Alice"))
            .body("additionalneeds", equalTo("Dinner"));
    }

   @Test(priority = 5 , dependsOnMethods = "testCreateBooking")
    public void testDeleteBooking() {
        given()
            .contentType("application/json")
            .header("Cookie", "token=" + token)
        .when()
            .delete("/booking/" + bookingId)
        .then()
            .statusCode(201);
    }
}
