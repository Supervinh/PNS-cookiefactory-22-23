package fr.unice.polytech.cf.interfaces;


import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.entities.cookies.Cookie;

import java.util.List;
import java.util.Optional;

public interface CatalogExplorer {

    List<Cookie> findByName(String name);

    Cookie getCookie(String name);

    List<Cookie> getCookies();

}

