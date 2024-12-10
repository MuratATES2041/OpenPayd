package tests;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ApiTest {

    // API Base URL
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";

    @Test
    public void testCountPostsPerUser() {
        // User ID and expected post count
        int[][] testCases = {{5, 10}, {7, 10}, {9, 10}};

        for (int[] testCase : testCases) {
            int userId = testCase[0];
            int expectedCount = testCase[1];

            // API call
            Response response = RestAssured.get(BASE_URL + "?userId=" + userId);

            // Validate response status
            Assert.assertEquals(response.getStatusCode(), 200, "Incorrect HTTP status code!");

            // Verify the number of posts
            List<?> posts = response.jsonPath().getList("");
            Assert.assertEquals(posts.size(), expectedCount,
                    "The number of posts for User ID: " + userId + " is different than expected!");
        }
    }

    @Test
    public void testUniqueIdPerPost() {
        // API call
        Response response = RestAssured.get(BASE_URL);

        // Validate response status
        Assert.assertEquals(response.getStatusCode(), 200, "Incorrect HTTP status code!");

        // Extract post IDs
        List<Integer> ids = response.jsonPath().getList("id");

        // Check for unique IDs
        HashSet<Integer> uniqueIds = new HashSet<>(ids);
        Assert.assertEquals(uniqueIds.size(), ids.size(), "Duplicate IDs found in the posts!");
    }

}