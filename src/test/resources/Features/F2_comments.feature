#Author: Ansu Biju
#Date: November 03, 2025

Feature: Comment API tests

  Scenario Outline: Create a new comment
    Given a comment with userId <userId>, foodId <foodId>, and body "<Comment>"
    When I send a POST request to create the comment
    Then the comment response status code should be 201
    And I store the comment id

    Examples:
      | userId | foodId | Commment   |
      | 1      | 5      | Very Tasty |
      | 2      | 6      | Too salty  |

  Scenario: Delete a comment
    Given a comment with userId 1, foodId 5, and body "Very tasty"
    When I send a POST request to create the comment
    And I store the comment id
    And I send a DELETE request for this comment
    Then the comment response status code should be 200

  Scenario: Get comment by user and food
    When I send a GET request to fetch comment by userId 1 and foodId 5
    Then the comment response status code should be 200
    And the response should contain "Very tasty"
