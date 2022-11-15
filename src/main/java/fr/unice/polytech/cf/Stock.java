package fr.unice.polytech.cf;

import fr.unice.polytech.cf.ingredients.*;

import java.util.*;

public class Stock {
    private final Map<Ingredient, Integer> stock;


    public Stock() {
        stock = new HashMap<>();
        initStock();
    }

    private void initStock() {
        stock.put(new Ingredient(IngredientEnum.DOUGH, "Plain", 2.2),5);
        stock.put(new Ingredient(IngredientEnum.DOUGH, "Chocolate", 3),10);
        stock.put(new Ingredient(IngredientEnum.DOUGH, "Peanutbutter", 1.5),3);
        stock.put(new Ingredient(IngredientEnum.DOUGH, "Oatmeal", 2),2);

        stock.put(new Ingredient(IngredientEnum.FLAVOUR, "Vanilla", 2),7);
        stock.put(new Ingredient(IngredientEnum.FLAVOUR, "Cinnamon", 2.5),10);
        stock.put(new Ingredient(IngredientEnum.FLAVOUR, "Chili", 3),5);

        stock.put(new Ingredient(IngredientEnum.TOPPING, "Whitechocolate", 1.5),3);
        stock.put(new Ingredient(IngredientEnum.TOPPING, "Milkchocolate", 2.5),7);
        stock.put(new Ingredient(IngredientEnum.TOPPING, "Mms", 3.5),8);
        stock.put(new Ingredient(IngredientEnum.TOPPING, "Reesesbuttercup", 1.5),4);
    }


    public Set<Ingredient> getIngredients() {
        return this.stock.keySet();
    }

    public Map<Ingredient, Integer> getStock() {
        return stock;
    }

    public void addIngredient(Ingredient ingredient, int quantity) {
        if (quantity < 1) throw new RuntimeException("Not a positive number of cookies");
        if (stock.containsKey(ingredient)) {
            stock.replace(ingredient, stock.get(ingredient) + quantity);
        } else {
            stock.put(ingredient, quantity);
        }
    }

    public void removeIngredient(Ingredient ingredient, int quantity) {
        if (quantity < 1) throw new RuntimeException("Not a positive number of ingredients");
        if (stock.containsKey(ingredient)) {
            if (stock.get(ingredient) >= quantity) {
                stock.replace(ingredient, stock.get(ingredient) - quantity);
            } else {
                throw new RuntimeException("Not enough ingredients in stock");
            }
        } else {
            throw new RuntimeException("No ingredients in stock");
        }
    }

    public void removeFromStock(int pAmount, Ingredient pIngredient) {
        int newAmount = stock.get(pIngredient) - pAmount;
        stock.replace(pIngredient, newAmount);
    }

    public boolean removeCookieFromStock(Cookie pCookie) {
        if (canBeRemoved(pCookie)) {
            for (Ingredient ingredient : pCookie.getIngredients()) {
                removeFromStock(1, ingredient);
            }
            return true;
        }
        return false;
    }

    public boolean canBeRemoved(Cookie pCookie) {
        for (Ingredient ingredient : pCookie.getIngredients()) {
            if (stock.get(ingredient) < 1) {
                throw new RuntimeException("Not enough ingredients, try another store");
            }
        }
        return true;
    }

    public boolean canBeRemove(Map<Ingredient, Integer> ingredientsToRemove){
        for (Ingredient ingredient : ingredientsToRemove.keySet()){
            if (stock.get(ingredient) < 1)
                return false;
        }
        return true;
    }
}
