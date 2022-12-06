package fr.unice.polytech.cf.interfaces;


import fr.unice.polytech.cf.entities.cookies.BasicCookie;

import java.util.Set;

public interface CatalogExplorator {

    Set<BasicCookie> listPreMadeRecipes();

    Set<BasicCookie> exploreCatalogue(String regexp);

}

