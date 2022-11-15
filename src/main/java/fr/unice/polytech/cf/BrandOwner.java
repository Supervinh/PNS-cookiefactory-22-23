package fr.unice.polytech.cf;

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
