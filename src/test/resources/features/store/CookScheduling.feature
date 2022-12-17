Feature: CookScheduling management

  Scenario: A cook is available and chosen for the next order
    Given the store myCucumberStore is open
    And the cook myCucumberCook is working in myCucumberStore with an empty schedule
    When a 25 minutes order comes in the myCucumberStore 's kitchen
    Then myCucumberCook should have 2 slot taken for that order


  Scenario: no cooks are available for the timeslot
    Given the store myCucumberStore is open
    And a store with no cook available
    When a 25 minutes order comes in the myCucumberStore 's kitchen
    Then the order is refused



    