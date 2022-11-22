package fr.unice.polytech.cf;

import fr.unice.polytech.cf.components.Catalog;
import fr.unice.polytech.cf.entities.cookies.Cookie;

public class BrandOwner {
    Catalog catalog;

    public BrandOwner(Catalog c){
        catalog = c;
    }

    public void removeCookie(Cookie cookie) {
        catalog.removeCookie(cookie);
    }

    public boolean acceptRecipe(Cookie c){
        return c.getPrice() < 3.5;
    }
}
