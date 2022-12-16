package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.*;
import fr.unice.polytech.cf.exceptions.OrderCancelledTwiceException;
import fr.unice.polytech.cf.exceptions.PaymentException;
import fr.unice.polytech.cf.interfaces.Bank;
import fr.unice.polytech.cf.interfaces.OrderProcessing;
import fr.unice.polytech.cf.interfaces.Payment;
import fr.unice.polytech.cf.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Component
public class Cashier implements Payment {

    private Bank bank;

    private OrderProcessing kitchen;

    private OrderRepository orderRepository;

    @Autowired
    public Cashier(Bank bank, OrderProcessing kitchen, OrderRepository orderRepository) {
        this.bank = bank;
        this.kitchen = kitchen;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order payOrder(Customer customer, Set<Item> items, Store store, LocalDateTime retrieve) throws PaymentException, OrderCancelledTwiceException {
        if(retrieve == null){
            retrieve = LocalDateTime.now().plusHours(1);
        }
        Order order = new Order(customer, items, store.getId(),retrieve);
        if(((new Date().getTime()-customer.getForbiddenToOrder().getTime())/60000)<10){
            throw new OrderCancelledTwiceException();
        }
        applyStoreTaxes(order, store);
        double price = order.getPrice();
        boolean status = false;
        status = bank.pay(customer, price);
        if (!status) {
            throw new PaymentException(customer.getName(), price);
        }
        orderRepository.save(order,order.getId());
        kitchen.prepareOrder(order);
        return order;
    }


    @Override
    public void applyStoreTaxes(Order order, Store store) {
        for (Item item : order.getItems()) {
            item.getCookie().setPrice(item.getCookie().getPrice() * (1 + store.getTaxes()));
        }
    }

}
