package fr.unice.polytech.cf;

import fr.unice.polytech.cf.cookies.Cookie;
import fr.unice.polytech.cf.cookies.PartyCookie;
import fr.unice.polytech.cf.exceptions.EmptyCartException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Cloneable {
    private Map<Cookie, Integer> cookies;
    private double price;
    private double cookingTime;

    private Store store;

    public Cart() {
        this.cookies = new HashMap<>();
        this.price = 0;
        this.cookingTime = 15;
        this.store = new Store("Default Store", LocalTime.of(10,0), LocalTime.of(19,30), new ArrayList<CookAccount>());
    }

    public Cart(Store store) {
        this.cookies = new HashMap<>();
        this.price = 0;
        this.cookingTime = 15;
        this.store = store;
    }

    public void addCookie(Cookie cookie, int number) {  //TODO check the store's opening time
        if (number < 1) throw new RuntimeException("Not a positive number of cookies");
        if(cookie.getClass()==PartyCookie.class && !store.canMakePartyCookie()) throw new RuntimeException("This store can't make party cookies");
        if (isEnoughIngredientsInStock(cookie)) {
            if (cookies.containsKey(cookie)) {
                cookies.replace(cookie, cookies.get(cookie) + number);
            } else {
                cookies.put(cookie, number);
            }
            price += cookie.getPrice() * number;
            cookingTime += cookie.getCookingTime() * number;
        }
    }

    private boolean isEnoughIngredientsInStock(Cookie cookie) {
        List<Cookie> cookiesToCheck = new ArrayList<>();
        cookiesToCheck.add(cookie);
        for (Cookie aCookie : cookies.keySet()){
            for (int i = 0; i< cookies.get(aCookie); i++)
                cookiesToCheck.add(aCookie);
        }
        return store.checkStock(cookiesToCheck);
    }

    public Map<Cookie, Integer> getCookies() {
        return new HashMap<>(cookies);
    }

    public double getPrice() {
        return price;
    }

    public double cookingTime() {
        return cookingTime;
    }

    public int getNbCookies() {
        int sum = 0;
        for (Integer e : cookies.values()) {
            sum += e;
        }
        return sum;
    }

    public Order confirmOrder() throws EmptyCartException, CloneNotSupportedException {return confirmOrder(false);}

    public Order confirmOrder(boolean isVIP) throws EmptyCartException, CloneNotSupportedException {
        if(isVIP) price = price - (price*0.1);
        if (!cookies.isEmpty()) {
            Order order = new Order((Cart) this.clone());
            store.removeCookiesFromStock(this.cookies);
            this.cookies = new HashMap<>();
            return order;
        } else {
            throw new EmptyCartException();
        }
    }

    public Store getStore() {
        return store;
    }
}
