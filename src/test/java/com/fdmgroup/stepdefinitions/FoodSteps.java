package com.fdmgroup.stepdefinitions;

import com.fdmgroup.pojos.Food;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FoodSteps {

    private static final String BASE_URL = "http://localhost:3000/foods";
    private Food food;
    private Response response;

    @Given("a food with id {int}, name {string}, and price {double}")
    public void a_food_with_id_name_and_price(int id, String name, double price) {
        food = new Food(id, name, price);
    }

    @When("I send a POST request to create the food")
    public void i_send_a_post_request_to_create_the_food() {
        response = given()
                .contentType(ContentType.JSON)
                .body(food)
            .when()
                .post(BASE_URL);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
        assertThat(response.getStatusCode(), is(expectedStatusCode));
    }

    @Given("a food exists with id {int}")
    public void a_food_exists_with_id(int id) {
        food = new Food();
        food.setId(id); 
    }

    @When("I update the price of food id {int} to {double}")
    public void i_update_the_price_of_food_id_to(int id, double newPrice) {
        String patchBody = "{\"price\": " + newPrice + "}";

        response = given()
                .contentType(ContentType.JSON)
                .body(patchBody)
            .when()
                .patch(BASE_URL + "/" + id);
    }


    @When("I send a GET request to fetch all foods")
    public void i_send_a_get_request_to_fetch_all_foods() {
        response = given()
                .contentType(ContentType.JSON)
            .when()
                .get(BASE_URL);
    }

    @Then("the response should contain a list of foods")
    public void the_response_should_contain_a_list_of_foods() {
        response.then().assertThat()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0].name", notNullValue())
                .body("[0].price", notNullValue());
    }

    @Then("the response should contain updated food price {double} for food id {int}")
    public void the_response_should_contain_updated_food_price_for_food_id(double expectedPrice, int id) {
        double actualPrice = response.jsonPath().getDouble("price");
        assertThat("Food id " + id + " price", actualPrice, equalTo(expectedPrice));
    }
}