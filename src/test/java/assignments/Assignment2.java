package assignments;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class Assignment2 {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api.restful-api.dev";
    }

    //  1. POST using HashMap
    @Test
    public void createProductUsingHashMap() {
        // Request body
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("year", 2019);
        dataMap.put("price", 1849.99);
        dataMap.put("CPUmodel", "Intel Core i9");
        dataMap.put("Harddisksize", "1 TB");

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("name", "Apple MacBook Pro 16");
        requestMap.put("data", dataMap);

        // Send request & store response
        Response response = RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(requestMap)
                .when()
                    .post("/objects");

        // Assertions
//        response.then().log().all();
        System.out.println(response.asPrettyString());
        
        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch");
        Assert.assertNotNull(response.jsonPath().getString("id"), "ID should not be null");
        Assert.assertEquals(response.jsonPath().getString("name"), "Apple MacBook Pro 16");
    }

    //  2. POST using POJO
    @Test
    public void createProductUsingPOJO() {
        // Create POJO data
        DataPojoClass dataObj = new DataPojoClass(2019, 1849.99, "Intel Core i9", "1 TB");
        ProductPojoClass productObj = new ProductPojoClass("Apple MacBook Pro 16", dataObj);

        // Send request & store response
        Response response = RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(productObj)
                .when()
                    .post("/objects");

        // Assertions
//        response.then().log().all();
        System.out.println(response.asPrettyString());
        
        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch");
        Assert.assertNotNull(response.jsonPath().getString("id"), "ID should not be null");
        Assert.assertEquals(response.jsonPath().getString("name"), "Apple MacBook Pro 16");
    }
}
