package fr.unice.polytech.cf;

import java.time.LocalDateTime;
import java.time.LocalTime;
import fr.unice.polytech.cf.cookies.Cookie;

import java.util.List;
import java.util.Map;

public class Order {
    private final Cart cart;
    private int CommandNumber=1;
    private final LocalDateTime retrieve;

    private OrderState commandState;
    private int orderNumber =1;
    private OrderState orderState;

    public Order(Cart cart){
        this.orderState = OrderState.UNPAID;
        this.cart=cart;
        retrieve = LocalDateTime.now().plusHours(1);
    }

    public Order(Cart cart, LocalDateTime retrieve){
        this.commandState=OrderState.UNPAID;
        this.cart=cart;
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
        String info=cart.getPrice()+"\n";
            Map<Cookie,Integer> allcookie=cart.getCookies();
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
    public int getCookingTime(){return cart.getCookingTime();}
    public int getNbCookies(){return cart.getNbCookies();}
    public double getPrice(){return cart.getPrice();}

    public Map<Cookie, Integer> getCookies(){return cart.getCookies();}

}
