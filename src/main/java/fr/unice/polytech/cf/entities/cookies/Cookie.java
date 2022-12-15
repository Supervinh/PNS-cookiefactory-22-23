package fr.unice.polytech.cf.entities.cookies;

import fr.unice.polytech.cf.entities.ingredients.Cooking;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.entities.ingredients.Mix;

import java.util.ArrayList;
import java.util.List;

public abstract class Cookie {

    String name;
    double price;
    int cookingTime;
    Cooking cooking;
    Ingredient dough;
    Ingredient flavour;
    Mix mix;
    List<Ingredient> topping;

    public String getName() {
        return name;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public double getPrice() {
        return price;
    }

    public double setPrice(double price) {
        return this.price = price;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public Ingredient getDough() {
        return dough;
    }

    public Ingredient getFlavour() {
        return flavour;
    }

    public List<Ingredient> getTopping() {
        return topping;
    }

    public List<Ingredient> getIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(dough);
        ingredients.add(flavour);
        for (Ingredient t : topping) {
            ingredients.add(t);
        }
        return ingredients;
    }
}

