package fr.unice.polytech.cf.entities.cookies;

import fr.unice.polytech.cf.entities.ingredients.*;

import java.util.ArrayList;
import java.util.List;

public class Cookie {
    private final String name;
    private double price;
    private double cookingTime;
    private Cooking cooking;
    private Ingredient dough;
    private Ingredient flavour;
    private Mix mix;
    private List<Ingredient> topping;

    public Cookie(String name) {
        this.name = name;
        this.price = 5;
    }

    public Cookie(String name, Cooking cooking, Ingredient dough, Ingredient flavour, Mix mix, List<Ingredient> toppings) {
        this.name = name;
        this.cooking = cooking;
        this.dough = dough;
        this.flavour = flavour;
        this.mix = mix;
        this.topping = new ArrayList<>();
        for (Ingredient t : toppings) {
            this.topping.add(t);
        }
        this.price = this.dough.getPrice() + this.flavour.getPrice() + this.topping.stream().mapToDouble(Ingredient::getPrice).sum();
        this.cookingTime = 5;
    }

    public String getName() {
        return name;
    }

    public double getCookingTime() {
        return cookingTime;
    }

    public double getPrice() {
        return price;
    }

    public Cooking getCooking() {
        return cooking;
    }

    public Ingredient getDough() {
        return dough;
    }

    public Ingredient getFlavour() {
        return flavour;
    }

    public Mix getMix() {
        return mix;
    }

    protected void setPrice(int i){price=i;}

    public List<Ingredient> getTopping() {
        return new ArrayList<>(topping);
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
