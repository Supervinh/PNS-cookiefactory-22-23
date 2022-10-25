package fr.unice.polytech.cf;

import fr.unice.polytech.cf.exceptions.EmptyCartException;

import java.util.ArrayList;
import java.util.List;

public class Cart implements Cloneable{
    private List<Cookie> cookies;

    private double price;

    public Cart() {
        this.cookies = new ArrayList<>();
        price = 0;
    }

    public void addCookie(Cookie cookie) {
        this.cookies.add(cookie);
        price += cookie.getPrice();
    }

    public ArrayList<Cookie> getCookies() {
        return new ArrayList<>(cookies);
    }

    public double getPrice() {
        return price;
    }

    public Order confirmOrder() throws CloneNotSupportedException, EmptyCartException {
        if(cookies.size()!=0) {
            Order order = new Order((Cart) this.clone());
            this.cookies = new ArrayList<>();
            return order;
        }
        else{
            throw new EmptyCartException();
        }
    }

}
