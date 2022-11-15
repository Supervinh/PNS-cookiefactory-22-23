package fr.unice.polytech.cf;

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
