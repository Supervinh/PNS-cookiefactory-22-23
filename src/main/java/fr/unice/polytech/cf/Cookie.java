package fr.unice.polytech.cf;

import fr.unice.polytech.cf.Ingredient;

public class Cookie {
    public int getAmount() {
        return amount;
    }

    private int amount;
    private Ingredient ingredient;

    public Cookie(Ingredient recipe, int amount) {
        this.ingredient = recipe;
        this.amount = amount;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
}
