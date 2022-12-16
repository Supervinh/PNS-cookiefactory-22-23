Feature: StoreOwnerAccount management

#  Scenario: The store's opening hour is after the new opening hour
#    Given the store's opening time is 13:00
#    When I change the opening time to 12:00
#    Then the store's opening time should be 12:00
#
#  Scenario: The store's opening hour is before the new opening hour
#    Given the store's opening time is 15:00
#    When I change the opening time to 16:00
#    Then the store's opening time should be 16:00
#
#  Scenario: The store's closing hour is after the new closing hour
#    Given the store's closing time is 19:00
#    When I change the closing time to 18:00
#    Then the store's closing time should be 18:00
#
#  Scenario: The store's closing hour is before the new closing hour
#    Given the store's closing time is 17:00
#    When I change the closing time to 19:00
#    Then the store's closing time should be 19:00
#
#  Scenario: The store's taxes are lower than the new taxes
#    Given the store's taxes are 0%
#    When I change the taxes to 5%
#    Then the store's taxes should be 5%
#    And the ingredients' prices should be taxed by 5%
#
#  Scenario: The store's taxes are higher than the new taxes
#    Given the store's taxes are 10%
#    When I change the taxes to 15%
#    Then the store's taxes should be 15%
#    And the ingredients' prices should be taxed by 15%
#
#  Scenario: The store's taxes are equal to the new taxes
#    Given the store's taxes are 10%
#    When I change the taxes to 10%
#    Then the store's taxes should be 10%
#    And the ingredients' prices should be taxed by 10%