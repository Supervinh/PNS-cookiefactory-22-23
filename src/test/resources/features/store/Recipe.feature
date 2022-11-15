Feature: Recipe management

  Scenario: adding a recipe to the catalog
    Given the brand's cook suggest a recipe new
    And it's accepted
    Then the cookie new should be in the catalog

  Scenario: not adding a recipe to the catalog
    Given the brand's cook suggests a recipe newer
    And it's not accepted
    Then the cookie newer shouldn't be in the catalog

  Scenario: deleting a recipe off the catalog
    Given the brand's cook suggest a recipe new
    And it's accepted
    When the brand owner deletes the cookie new
    Then the cookie new shouldn't be in the catalog