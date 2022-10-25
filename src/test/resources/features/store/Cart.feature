Feature: Cart management

  Scenario: adding a cookie to an empty cart
    Given the cart contains 0 cookies
    And the catalog contains the cookie chocolate
    When the client add 1 chocolate to the cart
    Then the cart should contain 1 cookies

  Scenario: adding a cookie to a cart already containing cookies
    Given the cart contains 3 cookies
    And the catalog contains the cookie chocolate
    When the client add 1 chocolate to the cart
    Then the cart should contain 4 cookies

  Scenario: confirm order with an empty cart
    Given the cart contains 0 cookies
    When the client confirm the order
    Then the client should get notified that the order is empty

  Scenario: confirm order with an non empty cart
    Given the cart contains 1 cookies
    When the client confirm the order
    Then the client should receive a purchase order
    And the cart should contain 0 cookies