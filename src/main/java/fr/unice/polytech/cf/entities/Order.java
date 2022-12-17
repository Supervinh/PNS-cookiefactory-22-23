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
    private OrderState orderState;

    public Order(Customer customer, Set<Item> cart, UUID storeId, LocalDateTime retrieve) {
        this.orderState = OrderState.UNPAID;
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
        return items.stream().mapToDouble(item -> item.getQuantity() * item.getCookie().getCookingTime()).sum() + 15;
    }

    public int getNbCookies() {
        return items.stream().mapToInt(Item::getQuantity).sum();
    }

    public double getPrice() {
        return Math.floor(items.stream().mapToDouble(item -> item.getQuantity() * item.getCookie().getPrice()).sum() * 100) / 100;
    }

    public UUID getId() {
        return id;
    }

    public UUID getStoreId() {
        return storeId;
    }
}
