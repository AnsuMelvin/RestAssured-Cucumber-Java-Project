#Author: Ansu Biju
#Date: November 03, 2025

Feature: Food API operations
  As a user
  I want to create, read, and update food items
  So that I can manage the food inventory

  Scenario Outline: Create a new food
    Given a food with id <id>, name "<name>", and price <price>
    When I send a POST request to create the food
    Then the response status code should be 201

    Examples:
      | id | name           | price |
      | 5  | pizza          | 7.5   |
      | 6  | pasta         	| 9.0   |
      | 7  | sushi			| 2.5   |

  Scenario Outline: Update food price
    Given a food exists with id <id>
    When I update the price of food id <id> to <price>
    Then the response status code should be 200

    Examples:
      | id | price |
      | 5  | 8.0   |
      | 6  | 10.0  |

  Scenario: Get all foods
    When I send a GET request to fetch all foods
    Then the response status code should be 200
    And the response should contain a list of foods