package fr.unice.polytech.cf.entities;

import fr.unice.polytech.cf.components.CatalogHandler;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;

public class BrandCook {
    CatalogHandler catalog;
    BrandOwner brandOwner;

    public BrandCook(BrandOwner b, CatalogHandler c){
        catalog = c;
        brandOwner = b;
    }

    public boolean addCookie(BasicCookie basicCookie) {
        if (brandOwner.acceptRecipe(basicCookie)) {
            catalog.addCookie(basicCookie);
            return true;
        }
        else {
            throw new RuntimeException("Too expensive");
        }
    }
}
