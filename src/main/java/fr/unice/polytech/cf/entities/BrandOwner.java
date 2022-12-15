package fr.unice.polytech.cf.entities;

import fr.unice.polytech.cf.components.CatalogHandler;
import fr.unice.polytech.cf.entities.cookies.CookieRecipe;

public class BrandOwner {
    CatalogHandler catalog;

    public BrandOwner(CatalogHandler c) {
        catalog = c;
    }

    public void removeCookie(CookieRecipe cookieRecipe) {
        catalog.removeCookie(cookieRecipe);
    }

    public boolean acceptRecipe(CookieRecipe c) {
        return c.getPrice() < 3.5;
    }
}
