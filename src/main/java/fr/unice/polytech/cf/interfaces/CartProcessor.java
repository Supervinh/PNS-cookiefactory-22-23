package fr.unice.polytech.cf.interfaces;

import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.cf.entities.Order;
import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.exceptions.EmptyCartException;

import java.util.List;
import java.util.Map;

public interface CartProcessor {

    Map<BasicCookie, Integer> getCookies();
    double getPrice(Customer customer);
    double getCookingTime(Customer customer);
    int getNbCookies(Customer customer);
    List<Ingredient> getIngredientsFromCart(Customer customer);
    Order confirmOrder(Customer customer) throws EmptyCartException, CloneNotSupportedException;
}
