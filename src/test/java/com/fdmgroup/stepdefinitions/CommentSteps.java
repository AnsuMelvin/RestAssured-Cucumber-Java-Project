package com.fdmgroup.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class CommentSteps {

    private Map<String, Object> commentData;
    private Response response;
    private String createdCommentId;

    @Given("a comment with userId {int}, foodId {int}, and body {string}")
    public void createCommentObject(int userId, int foodId, String body) {
        commentData = new HashMap<>();
        commentData.put("userId", userId);
        commentData.put("foodId", foodId);
        commentData.put("body", body);
    }

    @When("I send a POST request to create the comment")
    public void createComment() {
        response = given()
                .header("Content-Type", "application/json")
                .body(commentData)
                .when()
                .post("http://localhost:3000/comments");
    }

    @Then("the comment response status code should be {int}")
    public void validateCommentResponseCode(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    @And("I store the comment id")
    public void storeCommentId() {
        createdCommentId = response.jsonPath().getString("id");
    }

    @And("I send a DELETE request for this comment")
    public void deleteComment() {
        response = given()
                .when()
                .delete("http://localhost:3000/comments/" + createdCommentId);
    }

    @And("I send a GET request to fetch comment by userId {int} and foodId {int}")
    public void getCommentByUserAndFood(int userId, int foodId) {
        response = given()
                .queryParam("userId", userId)
                .queryParam("foodId", foodId)
                .when()
                .get("http://localhost:3000/comments");
    }

    @And("the response should contain {string}")
    public void validateCommentBody(String expectedBody) {
        String actualBody = response.jsonPath().getString("[0].body"); 
        assertThat(actualBody, containsString(expectedBody));
    }
}