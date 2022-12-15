package fr.unice.polytech.cf.entities.cookies;

import fr.unice.polytech.cf.entities.ingredients.*;

import java.util.ArrayList;
import java.util.List;

public class BasicCookie extends Cookie {

    public BasicCookie(String name) {
        this.name = name;
        this.price = 5;
    }

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
    }

    public void setPrice(int i){price=i;}

}
