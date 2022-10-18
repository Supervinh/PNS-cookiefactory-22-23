Feature: Order management

  Scenario: adding a cookie to an empty cart
    Given the cart contains 0 cookies
    When the client add 1 cookie(s) to the cart
    Then the cart should contain 1 cookies
