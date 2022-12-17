Feature: Recipe management

  Scenario: adding a recipe to the catalog
    Given the brand's cook thinks of a recipe myCucumberCookie
    When the brand's cook suggests the recipe
    Then the cookie myCucumberCookie should be in the catalog

  Scenario: not adding a recipe to the catalog
    Given the brand's cook thinks of a recipe myCucumberCookie too expensive
    When the brand's cook suggests the recipe
    Then the cookie myCucumberCookie shouldn't be in the catalog

#  Scenario: deleting a recipe off the catalog
#    Given the brand's cook suggest a recipe new
#    And it's accepted
#    When the brand owner deletes the cookie new
#    Then the cookie new shouldn't be in the catalog