package fr.unice.polytech.cf;

import fr.unice.polytech.cf.components.Catalog;
import fr.unice.polytech.cf.entities.cookies.Cookie;

public class BrandCook {
    Catalog catalog;
    BrandOwner brandOwner;

    public BrandCook(BrandOwner b, Catalog c){
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
