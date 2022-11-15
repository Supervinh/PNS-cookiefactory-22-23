package fr.unice.polytech.cf;

import fr.unice.polytech.cf.ingredients.Ingredient;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    private final String name;
    private Stock stock;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private Catalog catalog;

    public Store(String name, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.stock = new Stock();
        this.catalog = new Catalog();
    }

    public String getName() {
        return name;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime newOpeningTime) {
        openingTime = newOpeningTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime newClosingTime) {
        closingTime = newClosingTime;
    }

    public Map<Ingredient, Integer> getIngredients() {
        return stock.getStock();
    }

    public boolean hasCookie(String name) {
        return catalog.hasCookie(name);
    }

    public void addIngredient(Ingredient ingredient, int quantity) {
        stock.addIngredient(ingredient, quantity);
    }

    public void removeIngredient(Ingredient ingredient, int quantity) {
        stock.removeIngredient(ingredient, quantity);
    }

    public void removeCookieFromStock(Cookie cookie) {
        stock.removeCookieFromStock(cookie);
    }

    public void removeCookiesFromStock(Map<Cookie, Integer> cookies) {
        for(Cookie cookie : cookies.keySet()) {
            for (int i = 0 ; i < cookies.get(cookie); i++)
                removeCookieFromStock(cookie);
        }
    }

    public boolean isCookieAvailable(Cookie cookie) {
        return hasCookie(cookie.getName()) && stock.canBeRemoved(cookie);
    }

    public boolean isClosed(LocalTime time) {
        return time.isBefore(openingTime) || time.isAfter(closingTime);
    }

    public boolean isClosed() {
        return isClosed(LocalTime.now());
    }

    public boolean isOpen(LocalTime time) {
        return !isClosed(time);
    }

    public boolean isOpen() {
        return isOpen(LocalTime.now());
    }

    public boolean checkStock(List<Cookie> cookiesToCheck) {
        Map<Ingredient, Integer> cookiesIngredients = new HashMap<>();
        for (Cookie cookie : cookiesToCheck){
            for(Ingredient ingredient : cookie.getIngredients()){
                cookiesIngredients.put(ingredient, 1);
            }

        }
        return stock.canBeRemove(cookiesIngredients);
    }

    public Stock getStock(){
        return stock;
    }

}

