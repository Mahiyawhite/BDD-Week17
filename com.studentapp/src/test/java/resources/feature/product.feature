Feature: Testing different request on the product application

  @SMOKE
  Scenario: Check if the product application can be accessed by users
    When User sends a GET request to list endpoint
    Then User must get back a valid status code 200

  @POSITIVE
  Scenario Outline: Create a new product & verify if the product is added
    When I create a new product by providing the information name "<name>" type "<type>" price "<price>" upc "<upc>" shipping "<shipping>" description "<description>" manufacturer "<manufacturer>" model "<model>" url "<url>" image "<image>"
    Then I verify that the product with "<name>" is created
    Examples:
      |   name     | type  |  price |  upc  |  shipping  |  description  |  manufacturer  |   model  |
      | Balloon1   | Soft  |  4.99  |  011  |      1     |  celebrations |     Rubber     | MN2400B4Y|
      | Balloon2   | Soft  |  5.99  |  012  |      2     |  celebrations |     Rubber     | MN2400B4Z|


  Scenario Outline: Update a product detail & verify product is updated
    When I update the product with information firstName name "<name>" type "<type>" price "<price>" upc "<upc>" shipping "<shipping>" description "<description>" manufacturer "<manufacturer>" model "<model>" url "<url>" image "<image>"
    Then I delete the product data
    Examples:
      |   name     | type  |  price |  upc  |  shipping  |  description  |  manufacturer  |   model  |
      | Balloon1   | Soft  |  4.99  |  011  |      1     |  celebrations |     Rubber     | MN2400B4Y|
      | Balloon2   | Soft  |  5.99  |  012  |      2     |  celebrations |     Rubber     | MN2400B4Z|