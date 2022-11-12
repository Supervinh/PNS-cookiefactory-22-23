package fr.unice.polytech.cf;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private List<Order> Orders;

    OrderHistory(){Orders = new ArrayList<Order>();}

    public void addOrder(Order o){
        Orders.add(o);
    }

    public Order getOrder(int i){return Orders.get(i);}
    public List<Order> getOrders(){return new ArrayList<Order>(Orders);}
    public int getNbOrderedCookies(){
        int sum = 0;
        for(Order o: Orders){
            sum += o.getNbCookies();
        }
        return sum;
    }
    public int getNbOrders(){return Orders.size();}
}
