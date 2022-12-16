package fr.unice.polytech.cf.entities;

import java.util.*;

public class Customer {

    private final UUID id;
    private String name;
    private boolean isVIP;
    private String surname;
    private String mail;
    private int cookiesForVIP;

    private Date lastCancel;
    private Date forbiddenToOrder;

    private Set<Item> cart;

    public Customer(String name, String surname, String mail) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        id= UUID.randomUUID();
        cookiesForVIP = 0;
        lastCancel = new Date(1); // sets the Date to an old one
        forbiddenToOrder = new Date(1); // sets the Date to an old one
        cart = new HashSet<>();

    }

    public void setCookiesForVIP(int nb){cookiesForVIP = nb;}

    public int getCookiesForVIP(){return cookiesForVIP;}

    public boolean isVIP(){return isVIP;}
    public void setIsVIP(boolean b){isVIP=b;}

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Set<Item> getCart() {
        return cart;
    }

    public void setCart(Set<Item> cart) {
        this.cart = cart;
    }

    public void setForbiddenToOrder(Date d) {
        forbiddenToOrder = d;
    }
    public Date getForbiddenToOrder() {
        return forbiddenToOrder;
    }
    public void setLastCancel(Date d){lastCancel = d;}

    public Date getLastCancel() {
        return lastCancel;
    }

    public double getCookingTime() {
        double cookingTime = 15;
        for(Item i : cart){
            cookingTime += i.getCookie().getCookingTime()* i.getQuantity();

        }
        return cookingTime;
    }

}
