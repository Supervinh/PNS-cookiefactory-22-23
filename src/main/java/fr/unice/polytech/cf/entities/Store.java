package fr.unice.polytech.cf.entities;

import java.time.LocalTime;
import java.util.*;

public class Store {

    private UUID id;
    private final String name;
    private LocalTime openingTime;
    private LocalTime closingTime;

    private List<Item> cartTooGoodToGo;
    private boolean canMakePartyCookie;
    private double taxes;

    public Timer timer;

    public Store(String name, LocalTime openingTime, LocalTime closingTime) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.timer = new Timer();
        this.taxes = 0.0;
        canMakePartyCookie=false;


    }

    public Store(String name, double taxes, LocalTime openingTime, LocalTime closingTime) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.timer = new Timer();
        this.taxes = taxes;
        canMakePartyCookie=false;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    public double getTaxes() {
        return taxes;
    }
    public String getName() {
        return name;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime newOpeningTime) {
        openingTime = newOpeningTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime newClosingTime) {
        closingTime = newClosingTime;
    }

    public boolean isClosed(LocalTime time) {
        return time.isBefore(openingTime) || time.isAfter(closingTime);
    }

    public boolean isClosed() {
        return isClosed(LocalTime.now());
    }

    public boolean isOpen(LocalTime time) {
        return !isClosed(time);
    }

    public boolean isOpen() {
        return isOpen(LocalTime.now());
    }

    public List<Item> getCartTooGoodToGo() {
        return cartTooGoodToGo;
    }
    public void setCartTooGoodToGo(List<Item> cartTooGoodToGo) {
        this.cartTooGoodToGo = cartTooGoodToGo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}

