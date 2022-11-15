Feature: Catalog management

  Scenario: creating a catalog
    Given we create a catalog
    Then the catalog contains the cookie chocolat
    And the catalog contains the cookies Cinnamon
    And the catalog contains the cookies Plain
    And the catalog contains the cookies Vanilla


  Scenario: adding a cookie to the catalog
    Given we create a catalog
    And we add a cookie nutella to the catalog
    And we add a cookie fraise to the catalog
    Then the catalog contains the cookies nutella
    And the catalog contains the cookie fraise

  Scenario: Checking if no unwanted cookie are there
    Given we create a catalog
    And we add a cookie nutella to the catalog
    And we add a cookie fraise to the catalog
    Then the catalog does not contains the cookie pistache


    