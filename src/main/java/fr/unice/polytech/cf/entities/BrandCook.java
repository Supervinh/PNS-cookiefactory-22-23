package fr.unice.polytech.cf.entities;

import fr.unice.polytech.cf.components.CatalogHandler;
import fr.unice.polytech.cf.entities.cookies.Cookie;

public class BrandCook {
    CatalogHandler catalog;
    BrandOwner brandOwner;

    public BrandCook(BrandOwner b, CatalogHandler c){
        catalog = c;
        brandOwner = b;
    }

    public boolean addCookie(Cookie cookie) {
        if (brandOwner.acceptRecipe(cookie)) {
            catalog.addCookie(cookie);
            return true;
        }
        else {
            throw new RuntimeException("Too expensive");
        }
    }
}
