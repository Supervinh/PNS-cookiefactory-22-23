package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.cf.entities.Item;
import fr.unice.polytech.cf.entities.Order;
import fr.unice.polytech.cf.entities.OrderState;
import fr.unice.polytech.cf.exceptions.OrderNotPaidException;
import fr.unice.polytech.cf.exceptions.OrderNotReadyException;
import fr.unice.polytech.cf.interfaces.explorer.OrderFinder;
import fr.unice.polytech.cf.interfaces.modifier.OrderModifier;
import fr.unice.polytech.cf.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Component
public class OrderHandler implements OrderModifier, OrderFinder {

    private final OrderRepository orderRepository;


    @Autowired
    public OrderHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void cancelOrder(Customer customer, Order order) throws OrderNotPaidException {
        if (order.getOrderState() != OrderState.PAID) {
            throw new OrderNotPaidException(); // TODO : à changer en OrderBeingPreparedException
        } else {
            order.setOrderState(OrderState.CANCELLED);
            orderRepository.save(order, order.getId());
            if (((new Date().getTime() - customer.getLastCancel().getTime()) / 60000) < 8) { // less than 8 mins since last cancel
                customer.setForbiddenToOrder(new Date());
            }
            customer.setLastCancel(new Date());
        }
    }

    @Override
    public void retrieveOrder(Customer customer, Order order) throws OrderNotReadyException {
        if (order.getOrderState() == OrderState.READY) {
            order.setOrderState(OrderState.DELIVERED);
            customer.setCookiesForVIP(customer.getCookiesForVIP() + order.getNbCookies());
            if (customer.isVIP()) {
                customer.setIsVIP(false);
                customer.setCookiesForVIP(0);
            }
        } else {
            throw new OrderNotReadyException();
        }
    }

    @Override
    public String getReceipt(Order order) {
        if (order.getOrderState() != OrderState.UNPAID) {
            String info = order.getPrice() + "\n";
            Set<Item> cookies = order.getItems();
            for (Item item : cookies) {
                info += item.getQuantity() + " " + item.getCookie().getName() + "\n";
            }
            return info;
        } else {
            return ("commande non validée");
        }

    }

    @Override
    public List<Order> findOrdersByState(Enum<OrderState> state) {
        return null;
    }
}
