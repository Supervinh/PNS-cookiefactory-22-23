Feature: StoreOwnerAccount management

  Scenario: The store's opening hour is after the new opening hour
    Given the store's opening time is 13:00
    When I change the opening time to 12:00
    Then the store's opening time should be 12:00

  Scenario: The store's opening hour is before the new opening hour
    Given the store's opening time is 15:00
    When I change the opening time to 16:00
    Then the store's opening time should be 16:00

  Scenario: The store's closing hour is after the new closing hour
    Given the store's closing time is 19:00
    When I change the closing time to 18:00
    Then the store's closing time should be 18:00

  Scenario: The store's closing hour is before the new closing hour
    Given the store's closing time is 17:00
    When I change the closing time to 19:00
    Then the store's closing time should be 19:00