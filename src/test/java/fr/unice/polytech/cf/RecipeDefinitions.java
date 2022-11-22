package fr.unice.polytech.cf;

import fr.unice.polytech.cf.components.Catalog;
import fr.unice.polytech.cf.entities.cookies.Cookie;
import fr.unice.polytech.cf.ingredients.Cooking;
import fr.unice.polytech.cf.ingredients.Ingredient;
import fr.unice.polytech.cf.ingredients.IngredientEnum;
import fr.unice.polytech.cf.ingredients.Mix;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

public class RecipeDefinitions {
    Catalog c = new Catalog();
    BrandOwner brandOwner = new BrandOwner(c);
    BrandCook brandCook = new BrandCook(brandOwner, c);
    boolean accepted= false;

    @Given("the brand's cook suggest a recipe {word}")
    public void suggestReipe(String cookie){
        accepted = brandCook.addCookie(new Cookie(cookie, Cooking.CRUNCHY,
                new Ingredient(IngredientEnum.DOUGH, "Chocolate", 0.5),
                new Ingredient(IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>()));
    }
    @Given("the brand's cook suggests a recipe {word}")
    public void suggestsReipe(String cookie){
        try {
            accepted = brandCook.addCookie(new Cookie(cookie, Cooking.CRUNCHY,
                    new Ingredient(IngredientEnum.DOUGH, "Chocolate", 5),
                    new Ingredient(IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                    Mix.MIXED, new ArrayList<>()));
        } catch (RuntimeException e){}
    }

    @And("it's accepted")
    public void accepted(){assert accepted==true;}

    @And("it's not accepted")
    public void notAccepted(){assert accepted==false;}

    @When("the brand owner deletes the cookie {word}")
    public void deleteCookie(String cookie){
        brandOwner.removeCookie(c.getCookie(cookie));
    }

    @Then("the cookie {word} should be in the catalog")
    public void isInCatalog(String cookie){assert c.hasCookie(cookie)==true;}

    @Then("the cookie {word} shouldn't be in the catalog")
    public void isNotInCatalog(String cookie){assert c.hasCookie(cookie)==false;}
}
