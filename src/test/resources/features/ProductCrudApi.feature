Feature: Product Management API
  Scenario: Fetching an invalid product id
    When user wants to query the details of the product with id 100
    Then the result 'FAILS'