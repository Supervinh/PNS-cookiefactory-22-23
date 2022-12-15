package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.Order;
import fr.unice.polytech.cf.entities.OrderState;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderHandler {
    @Autowired
    private Order order;



    public OrderHandler(Order order){
        this.order=order;
    }


    public void payCommand() {
        if (order.getOrderState() == OrderState.UNPAID) {
            order.setOrderState(OrderState.PAID) ;
        }
    }

    public void delivered(){
        if(order.getOrderState() == OrderState.READY){
            order.setOrderState(OrderState.DELIVERED);
        }
    }

    public void cancelOrder(){
        if(order.getOrderState() == OrderState.PAID || order.getOrderState() == OrderState.UNPAID ){
            order.setOrderState(OrderState.CANCELLED);
        }
        else{
            System.out.println("your order can't be cancelled, it's already being prepared or ready");
        }
    }
}
