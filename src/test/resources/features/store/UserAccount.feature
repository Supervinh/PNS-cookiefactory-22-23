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