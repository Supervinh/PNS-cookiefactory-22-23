Feature: StoreOwnerAccount management

  Scenario: The store's opening hour is after the new opening hour
    Given the store myCucumberStore opens at 13:00
    When I change myCucumberStore's opening time to 12:00
    Then myCucumberStore's opening time should be 12:00

  Scenario: The store's opening hour is before the new opening hour
    Given the store myCucumberStore opens at 15:00
    When I change myCucumberStore's opening time to 16:00
    Then myCucumberStore's opening time should be 16:00

  Scenario: The store's closing hour is after the new closing hour
    Given the store myCucumberStore closes at 19:00
    When I change myCucumberStore's closing time to 18:00
    Then myCucumberStore's closing time should be 18:00

  Scenario: The store's closing hour is before the new closing hour
    Given the store myCucumberStore closes at 17:00
    When I change myCucumberStore's closing time to 19:00
    Then myCucumberStore's closing time should be 19:00

  Scenario: The store's taxes are lower than the new taxes
    Given the store myCucumberStore with a tax rate at 0%
    When I change myCucumberStore's tax rate to 5%
    Then myCucumberStore's tax rate should be 5%

  Scenario: The store's taxes are higher than the new taxes
    Given the store myCucumberStore with a tax rate at 10%
    When I change myCucumberStore's tax rate to 15%
    Then myCucumberStore's tax rate should be 15%

  Scenario: The store's taxes are equal to the new taxes
    Given the store myCucumberStore with a tax rate at 10%
    When I change myCucumberStore's tax rate to 10%
    Then myCucumberStore's tax rate should be 10%