package fr.unice.polytech.cf.interfaces;

import fr.unice.polytech.cf.entities.Order;
import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.exceptions.EmptyCartException;

import java.util.Map;

public interface CartProcessor {

    Map<BasicCookie, Integer> getCookies();
    double getPrice();
    int getCookingTime();
    int getNbCookies();
    Order confirmOrder(boolean isVIP) throws EmptyCartException, CloneNotSupportedException;
    Store getStore();
}
