package fr.unice.polytech.cf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Cart {
    private List<Cookie> cookies;

    public Cart() {
        this.cookies = new ArrayList<>();
    }

    public void addCookie(Cookie cookie) {
        this.cookies.add(cookie);
    }

    public ArrayList<Cookie> getCookies() {
        return new ArrayList<>(cookies);
    }

    public Order confirmOrder() throws CloneNotSupportedException {
        if(cookies.size()!=0) {
            Order order = new Order((Cart) this.clone());
            this.cookies = new ArrayList<>();
            return order;
        }
        else{
            System.out.println("your cart is empty");
            return null;
        }
    }

}
