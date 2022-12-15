package fr.unice.polytech.cf.entities.cookies;

import fr.unice.polytech.cf.entities.ingredients.Ingredient;

import java.util.List;
import java.util.UUID;

public class CookieRecipe extends Cookie {
    private final UUID id;

    public CookieRecipe(String name, double price, double cookingTime, Ingredient dough, Ingredient flavour, List<Ingredient> topping) {
        this.name = name;
        this.price = price;
        this.cookingTime = cookingTime;
        this.dough = dough;
        this.flavour = flavour;
        this.topping = topping;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }
}


