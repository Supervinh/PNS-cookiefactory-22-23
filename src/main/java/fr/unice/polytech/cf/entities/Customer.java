package fr.unice.polytech.cf.entities;

import java.util.*;

public class Customer {
    //private final List<Order> currentOrders;

    private final UUID id;
    private String name;
    private boolean isVIP;
    private String surname;
    private String mail;
    //private final OrderHistory Orders;
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

 /*   public List<Order> getCurrentOrders() {
        return new ArrayList<>(currentOrders);
    }
*/
    /*public void addOrder(Order newOrder) throws OrderCancelledTwiceException {
        if(((new Date().getTime()-forbiddenToOrder.getTime())/60000)<10){
            throw new OrderCancelledTwiceException();
        }
        currentOrders.add(newOrder);
    }*/

    /*public void retrieveOrder() throws OrderNotReadyException {    // Pour le moment nous récupérons la première commande de la liste
        if (currentOrders.get(0).getOrderState() != OrderState.READY) {
            throw new OrderNotReadyException();
        } else {
            currentOrders.get(0).Delivered();
            Orders.addOrder(currentOrders.get(0));
            cookiesForVIP += currentOrders.get(0).getNbCookies();
            currentOrders.remove(0);
            if (isVIP) {
                isVIP = false;
                cookiesForVIP = 0;
            }
        }
    }*/

/*    public void cancelOrder() throws OrderNotPaidException {
        if (currentOrders.get(0).getOrderState() != OrderState.PAID) {
            throw new OrderNotPaidException();
        } else {
            currentOrders.get(0).cancelOrder();
            currentOrders.remove(0);
            if(((new Date().getTime()-lastCancel.getTime())/60000)<8){ // less than 8 mins since last cancel
                forbiddenToOrder = new Date();
            }
            lastCancel = new Date();
        }
    }*/

    /*public void subscribeVIP() {
        if (cookiesForVIP >= 30 && !isVIP) isVIP = true;
    }*/

    public void setCookiesForVIP(int nb){cookiesForVIP = nb;}

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

    //public OrderHistory getOrderHistory() {return Orders;}
}
