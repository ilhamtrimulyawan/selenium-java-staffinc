Feature: API Testing with GET method
  Scenario Outline: Validate GET user response
    Given client send a GET request to the user endpoint for user <userId>
    Then the response status code should be <statusCode>
    And the first name should be "<firstName>"
    And the last name should be "<lastName>"
    And the email should be "<email>"

    Examples:
      | userId | statusCode | firstName | lastName | email                      |
      | 2      | 200        | Janet     | Weaver   | janet.weaver@reqres.in     |
      | 1      | 200        | George    | Bluth    | george.bluth@reqres.in     |
      | 3      | 200        | Emma      | Wong     | emma.wong@reqres.in        |