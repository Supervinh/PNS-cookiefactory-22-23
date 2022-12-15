package fr.unice.polytech.cf.entities;

import fr.unice.polytech.cf.components.CatalogHandler;
import fr.unice.polytech.cf.entities.cookies.CookieRecipe;

public class BrandCook {
    CatalogHandler catalog;
    BrandOwner brandOwner;

    public BrandCook(BrandOwner b, CatalogHandler c) {
        catalog = c;
        brandOwner = b;
    }

    public boolean addCookie(CookieRecipe cookieRecipe) {
        if (brandOwner.acceptRecipe(cookieRecipe)) {
            catalog.addCookie(cookieRecipe);
            return true;
        } else {
            throw new RuntimeException("Too expensive");
        }
    }
}
