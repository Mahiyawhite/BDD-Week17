Feature: Testing different request on the student application

  @SMOKE
  Scenario: Check if the student application can be accessed by users
    When User sends a GET request to list endpoint
    Then User must get back a valid status code 200

  @POSITIVE
  Scenario Outline: Create a new student & verify if the student is added
    When I create a new student by providing the information firstName "<firstName>" lastName "<lastName>" email "<email>" programme "<programme>" courses "<courses>"
    Then I verify that the student with "<email>" is created
    Examples:
      | firstName  | lastName  | email       | programme             | courses  |
      | Tanuja     | Patel     | 1@gmail.com | Application Programme | Java     |
      | Tanuja1    | Patel1    | 2@gmail.com | Api Testing           | selenium |

  Scenario Outline: Update a student detail & verify student is updated
    When I update the student with information firstName "<firstName>" lastName "<lastName>" email "<email>" programme "<programme>" courses "<courses>"
    Then I delete the student data
    Examples:
      | firstName  | lastName  | email       | programme             | courses  |
      | Tanuja     | Patel     | 1@gmail.com | Application Programme | Java     |

