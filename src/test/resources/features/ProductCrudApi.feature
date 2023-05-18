Feature: Product Management API

  Scenario: Fetching an non-existed product with id
    When user wants to query the details of the product with id 10000
    Then the result 'FAILS'

  Scenario: Create a new product
    Given a new product with the following attributes
      | productId | name  | shortDesc | price | salePrice | stock | sold | author |
      | 100       | Shoes | shoes     | 100.0 | 90.0      | 10    | 0    | me     |
    When user create new product
    Then the result 'IS SUCCESSFUL'

  Scenario: Fetching a valid student id
    Given user wants to query the details of the product with id 100
    Then the result 'IS SUCCESSFUL'

  Scenario: Deleting a student
    Given user wants to delete the product with id 100
    Then the result 'IS SUCCESSFUL'

  Scenario: Fetching an deleted product with id
    When user wants to query the details of the product with id 100
    Then the result 'FAILS'