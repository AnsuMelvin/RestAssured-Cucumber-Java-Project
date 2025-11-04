# Author: Ansu Biju
# Date: November 03, 2025
Feature: Manage Staffs under Managers

  Scenario: Update staff under a manager
    Given a STAFF manager with id 1
    When I send a STAFF PUT request to update staff under manager with data
      | id | name        | salary |
      | 0  | Amanda Brim | 550    |
      | 1  | Brenda Lee  | 250    |
    Then the STAFF response should contain updated staff data
      | id | name        | salary |
      | 0  | Amanda Brim | 550    |
      | 1  | Brenda Lee  | 250    |
    And the STAFF API response status code should be 200

  Scenario: Delete a staff under a manager
    Given a STAFF manager with id 1
    When I send a STAFF DELETE request to remove staff with id 2 under manager id 1
    Then the STAFF manager id 1 should have 2 staffs remaining
    And the STAFF API response status code should be 200
