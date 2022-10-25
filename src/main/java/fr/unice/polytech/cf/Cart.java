package fr.unice.polytech.cf;

import fr.unice.polytech.cf.exceptions.EmptyCartException;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Cloneable{
    private Map<Cookie, Integer> cookies;

    private double price;

    public Cart() {
        this.cookies = new HashMap<>();
        price = 0;
    }

    public void addCookie(Cookie cookie, int number) {
        if(cookies.containsKey(cookie)){
            cookies.replace(cookie, cookies.get(cookie)+number);
        } else {
            cookies.put(cookie, number);
        }
        price += cookie.getPrice()*number;
    }

    public Map<Cookie, Integer> getCookies() {
        return new HashMap<>(cookies);
    }

    public double getPrice() {
        return price;
    }

    public Order confirmOrder() throws  EmptyCartException, CloneNotSupportedException {
        if(cookies.size()!=0) {
            Order order = new Order((Cart) this.clone());
            this.cookies = new HashMap<>();
            return order;
        }
        else{
            throw new EmptyCartException();
        }
    }
}
