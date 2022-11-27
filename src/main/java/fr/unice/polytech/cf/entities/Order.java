package fr.unice.polytech.cf.entities;

import java.time.LocalDateTime;

import fr.unice.polytech.cf.components.CartHandler;
import fr.unice.polytech.cf.entities.cookies.Cookie;

import java.util.Map;

public class Order {
    private final CartHandler cartHandler;
    private int CommandNumber=1;
    private final LocalDateTime retrieve;

    private OrderState commandState;
    private int orderNumber =1;
    private OrderState orderState;

    public Order(CartHandler cartHandler){
        this.orderState = OrderState.UNPAID;
        this.cartHandler = cartHandler;
        retrieve = LocalDateTime.now().plusHours(1);
    }

    public Order(CartHandler cartHandler, LocalDateTime retrieve){
        this.commandState=OrderState.UNPAID;
        this.cartHandler = cartHandler;
        this.retrieve = retrieve;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public int getOrderNumber() {
        if(orderState != OrderState.UNPAID){
        return orderNumber;}
        else{
            return 0;
        }
    }

    public String getreceipt(){
        if(orderState !=OrderState.UNPAID){
        String info= cartHandler.getPrice()+"\n";
            Map<Cookie,Integer> allcookie= cartHandler.getCookies();
            for (Map.Entry mapentry : allcookie.entrySet()){
                Cookie b=(Cookie)mapentry.getKey();
                info=info+b.getName()+"\n";
            }
        return info;
        }
        else{
            return("commande non validé");
        }

    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public void paycommand() {
        if (orderState == OrderState.UNPAID) {
            orderState = OrderState.PAID;
        }
    }

    public void Delivered(){
        if(orderState == OrderState.READY){
            orderState = OrderState.DELIVERED;
        }
    }

    public void cancelOrder(){
        if(orderState == OrderState.PAID || orderState == OrderState.UNPAID ){
            orderState = OrderState.CANCELLED;
        }
        else{
            System.out.println("your order can't be cancelled, it's already being prepared or ready");
        }
    }


    public LocalDateTime getRetrieveDate(){return retrieve;}
    public int getCookingTime(){return cartHandler.getCookingTime();}
    public int getNbCookies(){return cartHandler.getNbCookies();}
    public double getPrice(){return cartHandler.getPrice();}

    public Map<Cookie, Integer> getCookies(){return cartHandler.getCookies();}

}
