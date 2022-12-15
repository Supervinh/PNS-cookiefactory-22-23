package fr.unice.polytech.cf.interfaces;

import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.entities.cookies.CookieRecipe;

public interface CatalogModifier {
    void updateCatalog(Store store);

    void addCookie(CookieRecipe cookieRecipe);

    void removeCookie(CookieRecipe cookieRecipe);
}
