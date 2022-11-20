package fr.unice.polytech.cf;

import fr.unice.polytech.cf.cookies.Cookie;
import fr.unice.polytech.cf.ingredients.Ingredient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Store {
    private final String name;
    private Stock stock;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private Catalog catalog;
    private List<Order> currentOrders;
    private OrderHistory storeOrderHistory;

    private CartTooGoodToGo cartTooGoodToGo;
    private CookScheduler storeSchedule;
    private boolean canMakePartyCookie;
    private double taxes;

    private Timer timer;

    public CookScheduler getStoreSchedule() {
        return storeSchedule;
    }

    public Store(String name, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.stock = new Stock();
        this.catalog = new Catalog(this.stock);
        this.currentOrders = new ArrayList<>();
        this.timer = new Timer();
        this.taxes = 0.0;
        this.applyTaxesToStock();
        storeSchedule = new CookScheduler(openingTime,closingTime, new ArrayList<>());
        canMakePartyCookie=canMakePartyCookie();
        updateTooGoodToGo();


    }

    public Store(String name, double taxes, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.stock = new Stock();
        this.catalog = new Catalog(this.stock);
        this.currentOrders = new ArrayList<>();
        this.timer = new Timer();
        this.taxes = taxes;
        this.applyTaxesToStock();
        storeSchedule = new CookScheduler(openingTime,closingTime, new ArrayList<>());
        canMakePartyCookie=canMakePartyCookie();
        updateTooGoodToGo();
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
        this.applyTaxesToStock();
    }

    public boolean assignOrder(Order o){
        currentOrders.add(o);
        return storeSchedule.assignOrder(o);
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
        catalog.updateCatalog(this.stock);
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

    public boolean canMakePartyCookie(){
        for (CookAccount c: getStoreSchedule().cooks) {
            if(c.canMakePartyCookie()) return true;
        }
        return false;
    }

    private void updateTooGoodToGo(){
        List<Order> ordersToGoodToGo = new ArrayList<>();
        for (Order o: currentOrders) {
            if(o.getOrderState()==OrderState.TOO_GOOD_TO_GO){
                ordersToGoodToGo.add(o);
                storeOrderHistory.addOrder(o);
                currentOrders.remove(o);
            }
            else if(o.getOrderState()==OrderState.DELIVERED || o.getOrderState()==OrderState.CANCELLED){
                storeOrderHistory.addOrder(o);
                currentOrders.remove(o);
            }
        }
        sendCartTooGoodToGo(new CartTooGoodToGo(this, ordersToGoodToGo));
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(isOpen())
                    updateTooGoodToGo();
                else{
                    timer.cancel();
                    timer.purge();
                    try {
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                updateTooGoodToGo();
                            }
                        }, new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().plusDays(1).atTime(openingTime).toString()));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    };
                }

            }
        }, 1000*60*180);
    }

    public void sendCartTooGoodToGo(CartTooGoodToGo cartTooGoodToGo){
        this.cartTooGoodToGo = cartTooGoodToGo;
        System.out.println("Sending cart too good to go");
        System.out.println(cartTooGoodToGo);
    }
}

