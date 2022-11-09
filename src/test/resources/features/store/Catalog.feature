Feature: Cart management

  Scenario: adding a cookie to an empty cart
    Given the cart contains 0 cookies chocolate
    And the catalog contains the cookie chocolate
    When the client add 1 chocolate to the cart
    Then the cart should contain 1 cookies

  Scenario: adding  no cookie to an empty cart
    Given the cart contains 0 cookies chocolate
    And the catalog contains the cookie chocolate
    When the client add 0 chocolate to the cart
    Then the cart should be empty

  Scenario: adding a cookie to a cart already containing cookies
    Given the cart contains 3 cookies chocolate
    And the catalog contains the cookie chocolate
    When the client add 1 chocolate to the cart
    Then the cart should contain 4 cookies chocolate

  Scenario: adding a cookie to a cart already containing cookies
    Given the cart contains 3 cookies chocolate
    And the catalog contains the cookie chocolate
    When the client add 1 caramel to the cart
    Then the cart should contain 3 cookies chocolate

  Scenario: adding a cookie to a cart already containing cookies
    Given the cart contains 3 cookies chocolate
    And the catalog contains the cookie chocolate
    When the client add 2 caramel to the cart
    Then the cart should contain 2 cookies caramel

  Scenario: adding a cookie to a cart already containing cookies
    Given the cart contains 3 cookies chocolate
    And the catalog contains the cookie chocolate
    When the client add 3 chocolate to the cart
    Then the cart should contain 6 cookies chocolate

  Scenario: confirm order with an empty cart
    Given the cart contains 0 cookies chocolate
    When the client confirm the order
    Then the client should get notified that the order is empty

  Scenario: confirm order with an non empty cart
    Given the cart contains 1 cookies chocolate
    When the client confirm the order
    Then the client should receive a purchase order
    And the cart should contain 0 cookies

  Scenario: adding cookie to cart
    Given the cart contains 3 cookies chocolate
    And the cookie chocolate price is 5.5
    Then the cart's price should be 16.5

  Scenario: adding cookie to cart
    Given the cart contains 3 cookies chocolate
    And the cookie chocolate price is 5.5
    And the cookie caramel price is 4.2
    When the client add 2 caramel to the cart
    Then the cart's price should be 24.9