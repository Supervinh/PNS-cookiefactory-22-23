package fr.unice.polytech.cf.cucumber;

import fr.unice.polytech.cf.components.CatalogHandler;
import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.entities.cookies.Cookie;
import fr.unice.polytech.cf.entities.ingredients.Cooking;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.entities.ingredients.IngredientEnum;
import fr.unice.polytech.cf.entities.ingredients.Mix;
import fr.unice.polytech.cf.exceptions.AlreadyExistingCustomerException;
import fr.unice.polytech.cf.interfaces.explorer.StockExplorer;
import fr.unice.polytech.cf.interfaces.modifier.StoreModifier;
import fr.unice.polytech.cf.repositories.CatalogRepository;
import fr.unice.polytech.cf.repositories.StoreRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.util.ArrayList;

public class RecipeDefinitions {
    @Autowired
    private CatalogHandler brandOwner;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreModifier storeModifier;

    private boolean accepted = false;
    private Store store;
    private Cookie cookie;
    private Exception exception;


    @Given("the brand's cook thinks of a recipe {word}")
    public void theBrandSCookThinksOfARecipe(String recipeName) {
        store = storeModifier.addStore("CucumberStore", LocalTime.of(8, 0), LocalTime.of(20, 0));
        cookie = new BasicCookie(recipeName, Cooking.CRUNCHY,
                new Ingredient(store.getId(), IngredientEnum.DOUGH, "Chocolate", 0.5),
                new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>());
    }

    @Given("the brand's cook suggests the recipe")
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
/*
    @When("the brand owner deletes the cookie {word}")
    public void deleteCookie(String cookie) {
        brandOwner.removeCookie(c.getCookie(cookie));
    }*/

    @Then("the cookie {word} should be in the catalog")
    public void isInCatalog(String cookie) {
        assert accepted;
    }

    @Then("the cookie {word} shouldn't be in the catalog")
    public void isNotInCatalog(String cookie) {
        assert !accepted;
    }
}
