package fr.unice.polytech.cf.entities;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class Order {
    private final LocalDateTime retrieve;
    private final UUID id;
    private final UUID storeId;
    private final Customer customer;
    private final Set<Item> items;
    private OrderState commandState;
    private final int orderNumber = 1;
    private OrderState orderState;

    /*public Order(Customer customer, Set<Item> cart, UUID storeId) {
        this.orderState = OrderState.UNPAID;
        this.customer = customer;
        this.items = cart;
        this.storeId = storeId;
        retrieve = LocalDateTime.now().plusHours(1);
        this.id = UUID.randomUUID();
    }*/

    public Order(Customer customer, Set<Item> cart, UUID storeId, LocalDateTime retrieve) {
        this.commandState = OrderState.UNPAID;
        this.customer = customer;
        this.items = cart;
        this.storeId = storeId;
        this.retrieve = retrieve;
        this.id = UUID.randomUUID();
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public Set<Item> getItems() {
        return items;
    }

    public LocalDateTime getRetrieveDate() {
        return retrieve;
    }

    public double getCookingTime() {
        return items.stream().mapToDouble(item -> item.getQuantity() * item.getCookie().getCookingTime()).sum();
    }

    public int getNbCookies() {
        return items.stream().mapToInt(Item::getQuantity).sum();
    }

    public double getPrice() {
        return items.stream().mapToDouble(item -> item.getQuantity() * item.getCookie().getPrice()).sum();
    }

    public UUID getId() {
        return id;
    }

    public UUID getStoreId() {
        return storeId;
    }
}
