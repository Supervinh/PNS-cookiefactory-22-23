package fr.unice.polytech.cf.entities;

import fr.unice.polytech.cf.components.CartHandlerTooGoodToGo;
import fr.unice.polytech.cf.components.CatalogHandler;
import fr.unice.polytech.cf.components.CookScheduler;
import fr.unice.polytech.cf.components.StockHandler;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.repositories.OrderHistory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Store {

    private UUID id;
    private final String name;
    private StockHandler stock;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private CatalogHandler catalog;
    private List<Order> currentOrders;
    private OrderHistory storeOrderHistory;

    private CartHandlerTooGoodToGo cartTooGoodToGo;
    private boolean canMakePartyCookie;
    private double taxes;

    private Timer timer;

    public Store(String name, LocalTime openingTime, LocalTime closingTime) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.stock = new StockHandler();
        this.catalog = new CatalogHandler(this.stock);
        this.currentOrders = new ArrayList<>();
        this.timer = new Timer();
        this.taxes = 0.0;
        this.applyTaxesToStock();
        canMakePartyCookie=canMakePartyCookie();
        updateTooGoodToGo();


    }

    public Store(String name, double taxes, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.stock = new StockHandler();
        this.catalog = new CatalogHandler(this.stock);
        this.currentOrders = new ArrayList<>();
        this.timer = new Timer();
        this.taxes = taxes;
        this.applyTaxesToStock();
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
        stock.removeIngredientByName(ingredient, quantity);
    }

    public void removeCookieFromStock(BasicCookie basicCookie) {
        stock.removeCookieFromStock(basicCookie);
    }

    public void removeCookiesFromStock(Map<BasicCookie, Integer> cookies) {
        for(BasicCookie basicCookie : cookies.keySet()) {
            for (int i = 0; i < cookies.get(basicCookie); i++)
                removeCookieFromStock(basicCookie);
        }
        catalog.updateCatalog(this.stock);
    }

    public boolean isCookieAvailable(BasicCookie basicCookie) {
        return hasCookie(basicCookie.getName()) && stock.canBeRemoved(basicCookie);
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

    public boolean checkStock(List<BasicCookie> cookiesToCheck) {
        Map<Ingredient, Integer> cookiesIngredients = new HashMap<>();
        for (BasicCookie basicCookie : cookiesToCheck){
            for(Ingredient ingredient : basicCookie.getIngredients()){
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

    public StockHandler getStock(){
        return stock;
    }

    public boolean canMakePartyCookie(){
        for (Cook c: getStoreSchedule().cooks) {
            if(c.canMakePartyCookie()) return true;
        }
        return false;
    }

    private void updateTooGoodToGo(){
        List<Order> ordersToGoodToGo = new ArrayList<>();
        for (Order o: currentOrders) {
            if(o.getOrderState()== OrderState.TOO_GOOD_TO_GO){
                ordersToGoodToGo.add(o);
                storeOrderHistory.addOrder(o);
                currentOrders.remove(o);
            }
            else if(o.getOrderState()==OrderState.DELIVERED || o.getOrderState()==OrderState.CANCELLED){
                storeOrderHistory.addOrder(o);
                currentOrders.remove(o);
            }
        }
        sendCartTooGoodToGo(new CartHandlerTooGoodToGo(this, ordersToGoodToGo));
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

    public void sendCartTooGoodToGo(CartHandlerTooGoodToGo cartTooGoodToGo){
        this.cartTooGoodToGo = cartTooGoodToGo;
        System.out.println("Sending cart too good to go");
        System.out.println(cartTooGoodToGo);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}

