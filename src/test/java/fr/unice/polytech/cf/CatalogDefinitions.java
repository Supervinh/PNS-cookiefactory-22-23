package fr.unice.polytech.cf;

import fr.unice.polytech.cf.components.CatalogHandler;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

public class CatalogDefinitions {
    @Autowired
    CatalogHandler catalogHandler;

    @Given("we create a catalog")
    public void we_create_a_catalog(){
        catalogHandler =new CatalogHandler();

    }
    @And("the catalog contains the cookie {word}")
    public void the_catalog_contains_cookie(String cookie) {
        if (!catalogHandler.hasCookie(cookie))
            catalogHandler.addCookie(new BasicCookie(cookie));

        assert(catalogHandler.hasCookie(cookie));
    }
    @Then("the catalog contains the cookies {word}")
    public void the_catalog_contains_cookies(String cookies){
        if (!catalogHandler.hasCookie(cookies))
            catalogHandler.addCookie(new BasicCookie(cookies));

        assert(catalogHandler.hasCookie(cookies));
    }
    @Then("the catalog doesn't contain the cookies {word}")
    public void the_catalog_doesnt_contain(String cookies){
        if (!catalogHandler.hasCookie(cookies))
            catalogHandler.addCookie(new BasicCookie(cookies));

        assert(!catalogHandler.hasCookie(cookies));
    }
    @And("we add a cookie {word} to the catalog")
    public void we_add_a_cookie_to_the_catalog(String cookie){
        catalogHandler.addCookie(new BasicCookie(cookie));
    }
}
