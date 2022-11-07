package fr.unice.polytech.cf;

import fr.unice.polytech.cf.ingredients.Dough;
import fr.unice.polytech.cf.ingredients.Flavour;
import fr.unice.polytech.cf.ingredients.Ingredient;
import fr.unice.polytech.cf.ingredients.Topping;

import java.util.HashMap;
import java.util.Map;

public class Stock {

    /*public int getAmount() {
        return amount;
    }

    private int amount;*/

    private Map<Ingredient, Integer> stock;


    public Stock() {
        stock = new HashMap<>();
        initStock();
    }

    private void initStock() {
        stock.put(Dough.PLAIN, 5);
        stock.put(Dough.CHOCOLATE, 4);
        stock.put(Dough.PEANUTBUTTER, 3);
        stock.put(Dough.OATMEAL, 2);

        stock.put(Flavour.VANILLA, 7);
        stock.put(Flavour.CINNAMON, 4);
        stock.put(Flavour.CHILI, 5);

        stock.put(Topping.WHITECHOCOLATE, 3);
        stock.put(Topping.MILKCHOCOLATE, 7);
        stock.put(Topping.MMS, 8);
        stock.put(Topping.REESESBUTTERCUP, 4);
    }


    /**
     * modify pAmount to the stock.
     * When pAmount is negative, the amount is retracted.
     *
     * @param pAmount
     * <code>true</code> otherwise.
     */
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
                throw new RuntimeException("Not enough ingredients");
            }
        }
        return true;
    }
}
