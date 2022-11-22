package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.Order;
import fr.unice.polytech.cf.Store;
import fr.unice.polytech.cf.entities.cookies.Cookie;
import fr.unice.polytech.cf.entities.cookies.PartyCookie;
import fr.unice.polytech.cf.exceptions.EmptyCartException;
import fr.unice.polytech.cf.interfaces.CartModifier;
import fr.unice.polytech.cf.interfaces.CartProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class CartHandler implements Cloneable, CartModifier, CartProcessor {
    protected Map<Cookie, Integer> cookies;
    double price;
    protected int cookingTime;

    private Store store;

    public CartHandler() {
        this.cookies = new HashMap<>();
        this.price = 0;
        this.cookingTime = 15;
        this.store = new Store("Default Store", LocalTime.of(10,0), LocalTime.of(19,30));
    }

    public CartHandler(Store store) {
        this.cookies = new HashMap<>();
        this.price = 0;
        this.cookingTime = 15;
        this.store = store;
    }

    @Override
    public void addCookie(Cookie cookie, int number) {  //TODO check the store's opening time
        if (number < 1) throw new RuntimeException("Not a positive number of cookies");
        if(cookie.getClass()==PartyCookie.class && !store.canMakePartyCookie()) throw new RuntimeException("This store can't make party cookies");
        if (isEnoughIngredientsInStock(cookie)) {
            if (cookies.containsKey(cookie)) {
                cookies.replace(cookie, cookies.get(cookie) + number);
            } else {
                cookies.put(cookie, number);
            }
            price += Math.floor(cookie.getPrice() * (1 + store.getTaxes()) * 100 * number)/100.0;
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

    @Override
    public Map<Cookie, Integer> getCookies() {
        return new HashMap<>(cookies);
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getCookingTime() {
        return cookingTime;
    }

    @Override
    public int getNbCookies() {
        int sum = 0;
        for (Integer e : cookies.values()) {
            sum += e;
        }
        return sum;
    }

    @Override
    public Order confirmOrder(boolean isVIP) throws EmptyCartException, CloneNotSupportedException {
        if(isVIP) price = price - (price*0.1);
        if (!cookies.isEmpty()) {
            Order order = new Order((CartHandler) this.clone());
            store.removeCookiesFromStock(this.cookies);
            this.cookies = new HashMap<>();
            return order;
        } else {
            throw new EmptyCartException();
        }
    }

    @Override
    public Store getStore() {
        return store;
    }
}
