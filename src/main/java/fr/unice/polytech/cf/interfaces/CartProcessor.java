package fr.unice.polytech.cf.interfaces;

import fr.unice.polytech.cf.Order;
import fr.unice.polytech.cf.Store;
import fr.unice.polytech.cf.entities.cookies.Cookie;
import fr.unice.polytech.cf.exceptions.EmptyCartException;

import java.util.Map;

public interface CartProcessor {

    Map<Cookie, Integer> getCookies();
    double getPrice();
    int getCookingTime();
    int getNbCookies();
    Order confirmOrder(boolean isVIP) throws EmptyCartException, CloneNotSupportedException;
    Store getStore();
}
