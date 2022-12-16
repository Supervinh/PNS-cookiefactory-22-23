package fr.unice.polytech.cf.interfaces;

import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.cf.entities.Item;
import fr.unice.polytech.cf.entities.Order;
import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.exceptions.EmptyCartException;
import fr.unice.polytech.cf.exceptions.OrderCancelledTwiceException;
import fr.unice.polytech.cf.exceptions.PaymentException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface CartProcessor {

    Map<BasicCookie, Integer> getCookies();
    double getPrice(Customer customer, Store store);
    double getCookingTime(Customer customer);
    int getNbCookies(Customer customer);
    List<Ingredient> getIngredientsFromCart(Customer customer);
    Order confirmOrder(Customer customer, Store store, LocalDateTime retrieve) throws EmptyCartException, CloneNotSupportedException, PaymentException, OrderCancelledTwiceException;
    boolean isEnoughIngredientsInStock(Item item, Store store, Customer customer);
}
