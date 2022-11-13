package fr.unice.polytech.cf;

import fr.unice.polytech.cf.ingredients.Ingredient;

import java.time.LocalTime;
import java.util.Map;

public class Store {
    private final String name;
    private final Stock stock;
    private LocalTime openingTime;
    private LocalTime closingTime;

    public Store(String name, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.stock = new Stock();
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

    public void addStock(Ingredient ingredient, int quantity) {
        stock.addIngredient(ingredient, quantity);
    }

    public void removeStock(Ingredient ingredient, int quantity) {
        stock.removeIngredient(ingredient, quantity);
    }

    public boolean isCookieAvailable(Cookie cookie) {
        return stock.canBeRemoved(cookie);
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

    /*public boolean isIngredientAvailable(Ingredient ingredient, int quantity) {
        return getStock(ingredient) >= quantity;
    }

    public boolean isIngredientAvailable(Ingredient ingredient, int quantity, LocalTime time) {
        return isOpen(time) && isIngredientAvailable(ingredient, quantity);
    }

    public boolean isIngredientAvailable(Ingredient ingredient, int quantity, boolean isClosed) {
        return isClosed || isIngredientAvailable(ingredient, quantity);
    }

    public boolean isIngredientAvailable(Ingredient ingredient, int quantity, LocalTime time, boolean isClosed) {
        return isIngredientAvailable(ingredient, quantity, isClosed) && isOpen(time);
    }*/


}

