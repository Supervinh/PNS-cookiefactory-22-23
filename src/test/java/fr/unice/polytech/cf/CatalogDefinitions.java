package fr.unice.polytech.cf;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CatalogDefinitions {
    Catalog catalog;

    @Given("we create a catalog")
    public void we_create_a_catalog(){
        catalog=new Catalog();

    }
    @And("the catalog contains the cookie {word}")
    public void the_catalog_contains_cookie(String cookie) {
        if (!catalog.hasCookie(cookie))
            catalog.addCookie(new Cookie(cookie));

        assert(catalog.hasCookie(cookie));
    }
    @Then("the catalog contains the cookies {word}")
    public void the_catalog_contains_cookies(String cookies){
        if (!catalog.hasCookie(cookies))
            catalog.addCookie(new Cookie(cookies));

        assert(catalog.hasCookie(cookies));
    }
    @Then("the catalog doesn't contain the cookies {word}")
    public void the_catalog_doesnt_contain(String cookies){
        if (!catalog.hasCookie(cookies))
            catalog.addCookie(new Cookie(cookies));

        assert(!catalog.hasCookie(cookies));
    }
    @And("we add a cookie {word} to the catalog")
    public void we_add_a_cookie_to_the_catalog(String cookie){
        catalog.addCookie(new Cookie(cookie));
    }
}
