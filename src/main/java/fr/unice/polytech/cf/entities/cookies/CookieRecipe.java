package fr.unice.polytech.cf.entities.cookies;

import fr.unice.polytech.cf.entities.ingredients.Cooking;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.entities.ingredients.Mix;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CookieRecipe extends CookieLabels implements Cookie {
    private final UUID id;

    public CookieRecipe(String name, Cooking cooking, Ingredient dough, Ingredient flavour, Mix mix, List<Ingredient> toppings) {
        this.name = name;
        this.cooking = cooking;
        this.dough = dough;
        this.flavour = flavour;
        this.topping = toppings;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getCookingTime() {
        return cookingTime;
    }

    @Override
    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(int i) {
        price = i;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public Ingredient getDough() {
        return dough;
    }

    @Override
    public Ingredient getFlavour() {
        return flavour;
    }

    @Override
    public List<Ingredient> getTopping() {
        return topping;
    }

    @Override
    public List<Ingredient> getIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(getDough());
        ingredients.add(getFlavour());
        for (Ingredient t : getTopping()) {
            ingredients.add(t);
        }

        return ingredients;
    }
}


