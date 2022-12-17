package fr.unice.polytech.cf.interfaces.explorer;


import fr.unice.polytech.cf.entities.cookies.Cookie;

import java.util.List;

public interface CatalogExplorer {

    List<Cookie> findByName(String name);

    Cookie getCookie(String name);

    List<Cookie> getCookies();

}

