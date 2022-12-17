package fr.unice.polytech.cf.cucumber;

import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.entities.cookies.Cookie;
import fr.unice.polytech.cf.entities.ingredients.Cooking;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.entities.ingredients.IngredientEnum;
import fr.unice.polytech.cf.entities.ingredients.Mix;
import fr.unice.polytech.cf.interfaces.modifier.CatalogModifier;
import fr.unice.polytech.cf.interfaces.modifier.StoreModifier;
import fr.unice.polytech.cf.repositories.CatalogRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.util.ArrayList;

public class RecipeDefinitions {
    @Autowired
    private CatalogModifier brandOwner;
    @Autowired
    private StoreModifier storeModifier;
    @Autowired
    private CatalogRepository catalogRepository;

    private boolean accepted = false;
    private Store store;
    private Cookie cookie;

    @Given("the brand's cook thinks of a recipe {word}")
    public void theBrandSCookThinksOfARecipe(String recipeName) {
        store = storeModifier.addStore("CucumberStore", LocalTime.of(8, 0), LocalTime.of(20, 0));
        cookie = new BasicCookie(recipeName, Cooking.CRUNCHY,
                new Ingredient(store.getId(), IngredientEnum.DOUGH, "Chocolate", 0.5),
                new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>());
    }

    @When("the brand's cook suggests the recipe")
    public void suggestReipe() {
        accepted = brandOwner.addCookie(cookie);

    }

    @Given("the brand's cook thinks of a recipe {word} too expensive")
    public void theBrandSCookThinksOfARecipeTooExpensive(String recipeName) {
        store = storeModifier.addStore("CucumberStore", LocalTime.of(8, 0), LocalTime.of(20, 0));
        cookie = new BasicCookie(recipeName, Cooking.CRUNCHY,
                new Ingredient(store.getId(), IngredientEnum.DOUGH, "Chocolate", 5.5),
                new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>());
    }

    @Then("the cookie {word} should be in the catalog")
    public void isInCatalog(String cookie) {
        assert accepted;
    }

    @Then("the cookie {word} shouldn't be in the catalog")
    public void isNotInCatalog(String cookie) {
        assert !accepted;
    }

    @Given("the catalog contains the cookie {word}")
    public void theCatalogContainsTheCookie(String recipeName) {
        catalogRepository.deleteAll();
        store = storeModifier.addStore("CucumberStore", LocalTime.of(8, 0), LocalTime.of(20, 0));
        cookie = new BasicCookie(recipeName, Cooking.CRUNCHY,
                new Ingredient(store.getId(), IngredientEnum.DOUGH, "Chocolate", 3.5),
                new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>());
        brandOwner.addCookie(cookie);
    }


    @When("the brand's cook deletes the cookie {word}")
    public void deleteCookie(String cookie) {
        for (Cookie c : catalogRepository.findAll()) {
            System.out.println(c.getName());
            if (c.getName().equals(cookie)) {
                brandOwner.removeCookie(c);
            }
        }

    }

}
