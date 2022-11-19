package fr.unice.polytech.cf;

import fr.unice.polytech.cf.cookies.Cookie;
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
    private List<CookAccount> cooks;
    private boolean canMakePartyCookie;
    private double taxes;

   public Store(String name, LocalTime openingTime, LocalTime closingTime, List<CookAccount> cookAccounts) {
        this.name = name;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.stock = new Stock();
        this.catalog = new Catalog();
        cooks = cookAccounts;
        canMakePartyCookie=canMakeCookie();
        this.taxes = 0.0;
        this.applyTaxesToStock();
   }

    public Store(String name, double taxes, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.stock = new Stock();
        this.catalog = new Catalog();
        this.taxes = taxes;
        this.applyTaxesToStock();
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
        this.applyTaxesToStock();
    }

    public double getTaxes() {
        return taxes;
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

    private void applyTaxesToStock(){
        for(Ingredient ingredient : stock.getStock().keySet()){
            ingredient.setStoreTax(taxes);
        }
    }

    public Stock getStock(){
        return stock;
    }

    private boolean canMakeCookie(){
        for (CookAccount c:cooks) {
            if(c.canMakePartyCookie()) return true;
        }
        return false;
    }

    public boolean canMakePartyCookie(){return canMakePartyCookie;}
}

