package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.Order;
import fr.unice.polytech.cf.Store;
import fr.unice.polytech.cf.entities.cookies.Cookie;

import java.util.List;

public class CartHandlerTooGoodToGo extends CartHandler {
    private double price;

    public CartHandlerTooGoodToGo(Store store, List<Order> orderList) {
        super(store);
        super.cookingTime = 0;
        for(Order o : orderList){
            price+=o.getPrice();
            for (Cookie c : o.getCookies().keySet()){
                super.addCookie(c, o.getCookies().get(c));
            }
        }
    }

    @Override
    public double getPrice() {
        return price * 0.3;
    }

    @Override
    public String toString() {
        return "CartTooGoodToGo{" +
                "price=" + price +
                ", cookies=" + cookies +
                '}';
    }
}
