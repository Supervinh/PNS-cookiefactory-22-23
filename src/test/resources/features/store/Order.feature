Feature: Order management

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

  Scenario: adding a cookie not contained in the catalog to an empty cart
    Given the cart contains 0 cookies
    And the catalog does not contains the cookie chocolate
    When the client add 1 chocolate to the cart
    Then the cart should contain 0 cookies

  Scenario: adding a cookie not contained in the catalog to a cart already containing cookies
    Given the cart contains 3 cookies
    And the catalog does not contains the cookie chocolate
    When the client add 1 chocolate to the cart
    Then the cart should contain 3 cookies

  Scenario: confirm order with an empty cart
    Given the cart contains 0 cookies
    When the client confirm the order
    Then the client should get notified that the order is empty

  Scenario: confirm order with an empty cart
    Given the cart contains 1 cookies
    When the client confirm the order
    Then the client should receive a purchase order
    And the cart should contain 0 cookies

  Scenario: The cook receive an order
    Given the cook is working and has 0 order
    When the cook receive an order
    Then the order's status should be WORKING_ON_IT

  Scenario: The cook receive an order
    Given the cook is working and has 1 order
    When the cook finishes to prepare the order
    Then the order's status should be READY

  Scenario: The order is not "paid"
    Given the cook is working and has 0 order
    When the cook receive an order
    And the order is not paid
    Then the order's status should be UNPAID