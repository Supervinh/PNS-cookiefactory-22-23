package fr.unice.polytech.cf;

import java.util.ArrayList;

public class CookAccount {
    private String name;
    private Order order;

    public CookAccount(String name) {
        this.name = name;
    }

    public CookAccount(String name, Order order) {
        this.name = name;
        this.order = order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void prepareOrder() {
      if (order.getCommandState() == CommandState.PAID){
          order.setCommandState(CommandState.WORKING_ON_IT);
      }
    }
    public void finishOrder(){
        if (order.getCommandState() == CommandState.WORKING_ON_IT){
            order.setCommandState(CommandState.READY);
        }
    }
    
    public Order getOrder(){
        return this.order;
    }
}
