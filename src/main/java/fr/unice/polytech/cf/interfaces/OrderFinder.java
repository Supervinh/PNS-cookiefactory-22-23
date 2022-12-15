package fr.unice.polytech.cf.interfaces;

import fr.unice.polytech.cf.entities.Order;
import fr.unice.polytech.cf.entities.OrderState;

import java.util.List;

public interface OrderFinder {
    List<Order> findOrdersByState(Enum<OrderState> state);
}
