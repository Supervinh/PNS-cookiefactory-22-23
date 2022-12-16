package fr.unice.polytech.cf.interfaces;

import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.entities.cookies.Cookie;

public interface CatalogModifier {
    void updateCatalog(Store store);

    void addCookie(Cookie cookie);

    void removeCookie(Cookie cookie);
}
