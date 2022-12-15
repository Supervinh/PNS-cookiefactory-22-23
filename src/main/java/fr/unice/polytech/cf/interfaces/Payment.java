package fr.unice.polytech.cf.interfaces;

import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.cf.entities.Item;
import fr.unice.polytech.cf.entities.Order;
import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.exceptions.OrderCancelledTwiceException;
import fr.unice.polytech.cf.exceptions.PaymentException;

import java.util.Set;

public interface Payment {

    Order payOrder(Customer customer, Set<Item> items, Store store) throws PaymentException, OrderCancelledTwiceException;
    void applyStoreTaxes(Order order, Store store);

}
