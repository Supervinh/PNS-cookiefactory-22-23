package fr.unice.polytech.cf.interfaces;

import fr.unice.polytech.cf.entities.cookies.Cookie;

public interface CartModifier {

    void addCookie(Cookie cookie, int number);
}
