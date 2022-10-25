package fr.unice.polytech.cf;

import fr.unice.polytech.cf.ingredients.Ingredient;

import java.time.LocalTime;
import java.util.*;

public class Store {
    private final String name;
    private final LocalTime openingTime;
    private final LocalTime closingTime;
    private Stock stock;

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

    public LocalTime getClosingTime() {
        return closingTime;
    }

    /*public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void addStock(Ingredient ingredient, int quantity) {
        stock.put(ingredient, quantity);
    }

    public int getStock(Ingredient ingredient) {
        return stock.getOrDefault(ingredient, 0);
    }

    public boolean isIngredientAvailable(Ingredient ingredient) {
        return getStock(ingredient) > 0;
    }

    public void removeStock(Ingredient ingredient, int quantity) {
        stock.put(ingredient, getStock(ingredient) - quantity);
    }*/

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

