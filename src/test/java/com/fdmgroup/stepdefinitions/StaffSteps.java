package com.fdmgroup.stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdmgroup.pojos.Manager;
import com.fdmgroup.pojos.Staff;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.closeTo;

public class StaffSteps {

    private Response response;
    private Manager manager;
    private ObjectMapper mapper = new ObjectMapper();

    @Given("a STAFF manager with id {int}")
    public void a_staff_manager_with_id(Integer managerId) throws Exception {
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .when()
                .get("/managers/" + managerId)
                .then().extract().response();
        manager = mapper.readValue(response.asString(), Manager.class);
    }

    @When("I send a STAFF PUT request to update staff under manager with data")
    public void i_send_staff_put_request(io.cucumber.datatable.DataTable dataTable) throws Exception {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {
            int staffId = Integer.parseInt(row.get("id"));
            String name = row.get("name");
            int salary = Integer.parseInt(row.get("salary"));

            // Update the staff in manager's staff list
            for (Staff s : manager.getStaffs()) {
                if (s.getId() == staffId) {
                    s.setName(name);
                    s.setSalary(salary);
                }
            }
        }

        // PUT updated manager back
        response = given()
                .header("Content-Type", "application/json")
                .body(mapper.writeValueAsString(manager))
                .when()
                .put("/managers/" + manager.getId())
                .then().extract().response();
    }

    @Then("the STAFF response should contain updated staff data")
    public void the_staff_response_should_contain_updated_staff_data(io.cucumber.datatable.DataTable dataTable) throws Exception {
        Manager updatedManager = mapper.readValue(response.asString(), Manager.class);
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {
            int staffId = Integer.parseInt(row.get("id"));
            String expectedName = row.get("name");
            int expectedSalary = Integer.parseInt(row.get("salary"));

            for (Staff s : updatedManager.getStaffs()) {
                if (s.getId() == staffId) {
                    assertThat(s.getName(), equalTo(expectedName));
                    // Convert to double and use a tolerance of 0.01
                    assertThat((double)s.getSalary(), closeTo((double)expectedSalary, 0.01));
                }
            }
        }
    }

    @When("I send a STAFF DELETE request to remove staff with id {int} under manager id {int}")
    public void i_send_staff_delete_request(Integer staffId, Integer managerId) throws Exception {
        // Remove staff
        manager.getStaffs().removeIf(s -> s.getId() == staffId);

        // PUT updated manager back
        response = given()
                .header("Content-Type", "application/json")
                .body(mapper.writeValueAsString(manager))
                .when()
                .put("/managers/" + managerId)
                .then().extract().response();
    }

    @Then("the STAFF manager id {int} should have {int} staffs remaining")
    public void the_staff_manager_should_have_staffs_remaining(Integer managerId, Integer expectedCount) throws Exception {
        Manager updatedManager = mapper.readValue(response.asString(), Manager.class);
        assertThat(updatedManager.getStaffs().size(), equalTo(expectedCount));
    }

    @Then("the STAFF API response status code should be {int}")
    public void the_staff_api_response_status_code_should_be(Integer statusCode) {
        assertThat(response.statusCode(), equalTo(statusCode));
    }
}