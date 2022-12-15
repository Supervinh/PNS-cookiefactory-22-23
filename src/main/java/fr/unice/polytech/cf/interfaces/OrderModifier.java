package fr.unice.polytech.cf.interfaces;

import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.cf.entities.Order;
import fr.unice.polytech.cf.exceptions.OrderNotPaidException;
import fr.unice.polytech.cf.exceptions.OrderNotReadyException;

public interface OrderModifier {
    String getReceipt(Order order);

    void cancelOrder(Customer customer, Order order) throws OrderNotPaidException;

    void retrieveOrder(Customer customer, Order order) throws OrderNotReadyException;
}
