package fr.unice.polytech.cf.entities;

import java.time.LocalDateTime;

import fr.unice.polytech.cf.components.CartHandler;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.entities.cookies.Cookie;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Order {
    private UUID id;

    private UUID storeId;

    private Customer customer;

    private Set<Item> items;

    private final LocalDateTime retrieve;

    private OrderState commandState;
    private int orderNumber =1;
    private OrderState orderState;

    public Order(Customer customer, Set<Item> cart, UUID storeId){
        this.orderState = OrderState.UNPAID;
        this.customer = customer;
        this.items = cart;
        this.storeId = storeId;
        retrieve = LocalDateTime.now().plusHours(1);
        this.id = UUID.randomUUID();
    }

    public Order(Customer customer, Set<Item> cart, UUID storeId, LocalDateTime retrieve){
        this.commandState=OrderState.UNPAID;
        this.customer = customer;
        this.items = cart;
        this.storeId = storeId;
        this.retrieve = retrieve;
        this.id= UUID.randomUUID();
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public Set<Item> getItems(){
        return items;
    }


    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }


    public LocalDateTime getRetrieveDate(){return retrieve;}
    public int getCookingTime(){return items.stream().mapToInt(item -> item.getQuantity() * item.getCookie().getCookingTime()).sum();}
    public int getNbCookies(){return items.stream().mapToInt(Item::getQuantity).sum();}
    public double getPrice(){return items.stream().mapToDouble(item -> item.getQuantity() * item.getCookie().getPrice()).sum();}

    public UUID getId() {
        return id;
    }

    public UUID getStoreId() {
        return storeId;
    }
}
