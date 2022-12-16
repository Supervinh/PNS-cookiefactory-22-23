package fr.unice.polytech.cf.entities.cookies;

import fr.unice.polytech.cf.entities.ingredients.Cooking;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.entities.ingredients.Mix;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BasicCookie extends CookieLabels implements Cookie {

    public BasicCookie(String name, Cooking cooking, Ingredient dough, Ingredient flavour, Mix mix, List<Ingredient> toppings) {
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
        this.id = UUID.randomUUID();
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

    @Override
    public UUID getId() {
        return id;
    }
}
