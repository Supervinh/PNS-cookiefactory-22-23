package fr.unice.polytech.cf.interfaces;

import fr.unice.polytech.cf.entities.cookies.BasicCookie;

public interface CartModifier {

    void addCookie(BasicCookie basicCookie, int number);
}
