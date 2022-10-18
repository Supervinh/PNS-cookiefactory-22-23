package fr.unice.polytech.cf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Cart {
    private List<Cookie> cookies;

    public Cart() {
        this.cookies = new ArrayList<>();
    }
    public Order ConfirmOrder(){
        Order order=new Order(this);
        return order;
    }
    public Order ConfirmOrder(Store store){
        Order order=new Order(this,store);
        return order;
    }
    public void addCookie(Cookie cookie) {
        this.cookies.add(cookie);
    }

    public Collection<Object> getCookies() {
        return new ArrayList<>(cookies);
    }
}
