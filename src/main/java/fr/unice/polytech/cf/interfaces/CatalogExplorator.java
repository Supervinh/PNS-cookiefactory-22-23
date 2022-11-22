package fr.unice.polytech.cf.interfaces;


import fr.unice.polytech.cf.entities.cookies.Cookie;

import java.util.Set;

public interface CatalogExplorator {

    Set<Cookie> listPreMadeRecipes();

    Set<Cookie> exploreCatalogue(String regexp);

}

