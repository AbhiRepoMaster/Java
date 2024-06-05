package javabasics;

import static io.restassured.RestAssured;
import static io.restassured.RestAssured;
import static io.restassured.RestAssured;

public class ApiTesting {

    public static void main(String[] args) {
        // Set base URI of the API
        RestAssured.baseURI = "https://api.example.com";

        // Make a GET request to a specific endpoint
        Response response = RestAssured.get("/endpoint");

        // Get response body as a string
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        // Get status code
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);

        // Validate status code
        if (statusCode == 200) {
            System.out.println("API is working fine.");
        } else {
            System.out.println("API is not working as expected.");
        }

        // Validate response body
        if (responseBody.contains("expected_value")) {
            System.out.println("Response body contains expected value.");
        } else {
            System.out.println("Response body does not contain expected value.");
        }
    }
}
