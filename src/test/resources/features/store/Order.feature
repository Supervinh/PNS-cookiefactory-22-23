Feature: Order management

  Scenario: adding a cookie not contained in the catalog to an empty cart
    Given the cart contains 0 cookies chocolate
    And the catalog does not contains the cookie cramberry
    When the client add 1 cramberry to the cart
    Then the cart should contain 0 cookies

  Scenario: adding a cookie not contained in the catalog to a cart already containing cookies
    Given the cart contains 3 cookies chocolate
    And the catalog does not contains the cookie cramberry
    When the client add 1 cramberry to the cart
    Then the cart should contain 3 cookies

  Scenario: The cook receive an order
    Given the cook is working and has 0 order
    When the cook receive a paid order
    Then the order's status should be WORKING_ON_IT

  Scenario: The cook receive an order
    Given the cook is working and has 1 order
    When the cook finishes to prepare the order
    Then the order's status should be READY

  Scenario: The order is not "paid"
    Given the cook is working and has 0 order
    When the cook receive an unpaid order
    Then the order's status should be UNPAID

  Scenario: The client tries to retrieve a finished order
    Given the client has made an order
    And the client's order is ready
    When the client comes to retrieve the order
    Then the order's status should be DELIVERED

  Scenario: The client tries to retrieve an unfinished order
    Given the client has made an order
    And the client's order is not ready
    When the client comes to retrieve the order
    Then the order's status shouldn't be delivered

   Scenario: the client want a receipt
     Given  the client has made an order
     And  the order is paid
     Then the client receive a receipt for his last order


