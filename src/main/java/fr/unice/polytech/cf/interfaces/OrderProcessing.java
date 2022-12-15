package fr.unice.polytech.cf.interfaces;

import fr.unice.polytech.cf.entities.Order;
import fr.unice.polytech.cf.entities.Store;

public interface OrderProcessing {

    void prepareOrder(Order order);

    void finishOrder(Order order);

    boolean assignOrder(Order order, Store store);



}
