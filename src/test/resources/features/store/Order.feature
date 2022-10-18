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
