package fr.unice.polytech.cf.entities;

import fr.unice.polytech.cf.components.CatalogHandler;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;

public class BrandOwner {
    CatalogHandler catalog;

    public BrandOwner(CatalogHandler c) {
        catalog = c;
    }

    public void removeCookie(BasicCookie basicCookie) {
        catalog.removeCookie(basicCookie);
    }

    public boolean acceptRecipe(BasicCookie c) {
        return c.getPrice() < 3.5;
    }
}
