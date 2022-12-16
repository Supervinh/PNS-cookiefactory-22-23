package fr.unice.polytech.cf.interfaces.modifier;

import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.cf.entities.Item;
import fr.unice.polytech.cf.entities.Store;

public interface CartModifier {

    void addCookie(Customer customer, Store store, Item item);

    boolean isEnoughIngredientsInStock(Item item, Store store, Customer customer);
}
