Feature: UserAccount management

  Scenario: subscribing correctly to VIP
    Given the account has ordered 32 cookies
    And he isn't VIP
    When he subscribes to the VIP
    Then he should be VIP

  Scenario: not subscribing correctly to VIP
    Given the account has ordered 20 cookies
    And he isn't VIP
    When he subscribes to the VIP
    Then he shouldn't be VIP

  Scenario: re-subscribing to VIP
    Given the account has ordered 32 cookies
    And he is VIP
    When he subscribes to the VIP
    Then he should be VIP

  Scenario: not subscribing correctly to VIP
    Given the account has ordered 20 cookies
    And he isn't VIP
    When he subscribes to the VIP
    Then he shouldn't be VIP

  Scenario: history works correctly
    Given he has made an order
    And his order is ready
    When he comes to retrieve the order
    Then his order history should have 1 orders
    And his current order should have 0 orders

  Scenario: The customer cancels a paid order

    Given he has made an order
    And his order is paid
    When he cancels the order
    Then the order should be removed

  Scenario: The customer cancels an order being prepared
    Given he has made an order
    And his order is working_on_it
    When he cancels the order
    Then the CommandState should be working_on_it

  Scenario: The customer tried to order while being forbidden to
    Given he has made an order
    And the customer is forbidden to order
    When he orders something
    Then he should receive an error

