package fr.unice.polytech.cf.cucumber;

public class RecipeDefinitions {
    /*CatalogHandler c = new CatalogHandler();
    BrandOwner brandOwner = new BrandOwner(c);
    BrandCook brandCook = new BrandCook(brandOwner, c);
    boolean accepted= false;

    @Given("the brand's cook suggest a recipe {word}")
    public void suggestReipe(String cookie){
        accepted = brandCook.addCookie(new BasicCookie(cookie, Cooking.CRUNCHY,
                new Ingredient(storeId, IngredientEnum.DOUGH, "Chocolate", 0.5),
                new Ingredient(storeId, IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>()));
    }
    @Given("the brand's cook suggests a recipe {word}")
    public void suggestsReipe(String cookie){
        try {
            accepted = brandCook.addCookie(new BasicCookie(cookie, Cooking.CRUNCHY,
                    new Ingredient(storeId, IngredientEnum.DOUGH, "Chocolate", 5),
                    new Ingredient(storeId, IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
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
    public void isNotInCatalog(String cookie){assert c.hasCookie(cookie)==false;}*/
}
