package fr.unice.polytech.cf.interfaces;


import fr.unice.polytech.cf.entities.cookies.CookieRecipe;

import java.util.List;
import java.util.Optional;

public interface CatalogExplorer {

    Optional<CookieRecipe> findByName(String name);

    CookieRecipe getCookie(String name);

    List<CookieRecipe> getCookies();

    boolean hasCookie(String name);


}

