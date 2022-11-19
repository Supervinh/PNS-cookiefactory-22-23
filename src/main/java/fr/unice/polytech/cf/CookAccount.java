package fr.unice.polytech.cf;

public class CookAccount {
    private String name;
    private Order order;
    private boolean canMakePartyCookie;

    public CookAccount(String name, boolean b) {
        this.name = name;
        canMakePartyCookie = b;
    }

    public CookAccount(String name, Order order) {
        this.name = name;
        this.order = order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void prepareOrder() {
      if (order.getOrderState() == OrderState.PAID){
          order.setOrderState(OrderState.WORKING_ON_IT);
      }
    }
    public void finishOrder(){
        if (order.getOrderState() == OrderState.WORKING_ON_IT){
            order.setOrderState(OrderState.READY);
        }
    }

    public Order getOrder(){
        return this.order;
    }

    public boolean canMakePartyCookie(){return canMakePartyCookie;}
}
