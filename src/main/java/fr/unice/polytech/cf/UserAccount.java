package fr.unice.polytech.cf;

import fr.unice.polytech.cf.exceptions.OrderNotReadyException;

import java.util.ArrayList;
import java.util.List;

public class UserAccount {
    private final List<Order> currentOrders;
    private String name;
    private boolean isVIP;
    private String surname;
    private String mail;
    private final OrderHistory Orders;
    private int cookiesForVIP;

    public UserAccount() {
        currentOrders = new ArrayList<>();
        Orders = new OrderHistory();
        cookiesForVIP = 0;
    }

    public List<Order> getCurrentOrders() {
        return new ArrayList<>(currentOrders);
    }

    public void addOrder(Order newOrder) {
        currentOrders.add(newOrder);
    }

    public void printReceipt(Order order) {
        order.getreceipt();
    }

    public void retrieveOrder() throws OrderNotReadyException {    // Pour le moment nous récupérons la première commande de la liste
        if (currentOrders.get(0).getCommandState() != CommandState.READY) {
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
    }

    public void subscribeVIP() {
        if (cookiesForVIP >= 30 && !isVIP) isVIP = true;
    }

    public void setCookiesForVIP(int nb){cookiesForVIP = nb;}

    public boolean isVIP(){return isVIP;}
    public void setIsVIP(boolean b){isVIP=b;}

    public OrderHistory getOrderHistory() {return Orders;}
}
