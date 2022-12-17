package fr.unice.polytech.cf.interfaces.modifier;

import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.entities.cookies.Cookie;

public interface CatalogModifier {
    void updateCatalog(Store store);

    boolean addCookie(Cookie cookie);

    void removeCookie(Cookie cookie);

    boolean acceptRecipe(Cookie cookie);
}
