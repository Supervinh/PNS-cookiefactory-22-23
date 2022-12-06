package fr.unice.polytech.cf.entities;

import fr.unice.polytech.cf.components.CatalogHandler;
import fr.unice.polytech.cf.entities.cookies.Cookie;

public class BrandOwner {
    CatalogHandler catalog;

    public BrandOwner(CatalogHandler c){
        catalog = c;
    }

    public void removeCookie(Cookie cookie) {
        catalog.removeCookie(cookie);
    }

    public boolean acceptRecipe(Cookie c){
        return c.getPrice() < 3.5;
    }
}
