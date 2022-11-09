package fr.unice.polytech.cf;

import io.cucumber.java.en.And;

public class CatalogDefinitions {
    Catalog catalog;
    boolean possible;

    @And("the catalog contains the cookie {word}")
    public void the_catalog_contains_cookie(String cookie) {
        if (!catalog.hasCookie(cookie))
            catalog.addCookie(new Cookie(cookie));
        possible = true;
        assert(catalog.hasCookie(cookie));
    }
}
