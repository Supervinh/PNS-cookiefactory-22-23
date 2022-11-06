package fr.unice.polytech.cf;

import fr.unice.polytech.cf.ingredients.*;

import java.util.ArrayList;
import java.util.List;

public class Cookie {
    private final String name;
    private final double price;
    private double cookingTime;
    private Cooking cooking;
    private Dough dough;
    private Flavour flavour;
    private Mix mix;
    private List<Topping> topping;

    public Cookie(String name) {
        this.name = name;
        this.price = 5;
    }

    public Cookie(String name, Cooking cooking, Dough dough, Flavour flavour, Mix mix, List<Topping> topping) {
        this.name = name;
        this.cooking = cooking;
        this.dough = dough;
        this.flavour = flavour;
        this.mix = mix;
        this.topping = new ArrayList<>(topping);
        this.price = this.dough.getPrice() + this.flavour.getPrice() + this.topping.stream().mapToDouble(Topping::getPrice).sum();
        this.cookingTime = 5;
    }

    public Cookie(String name, Cooking cooking, Dough dough, Flavour flavour, Mix mix, List<Topping> topping, int cookingTime) {
        this.name = name;
        this.cooking = cooking;
        this.dough = dough;
        this.flavour = flavour;
        this.mix = mix;
        this.topping = new ArrayList<>(topping);
        this.price = this.dough.getPrice() + this.flavour.getPrice() + this.topping.stream().mapToDouble(Topping::getPrice).sum();
        this.cookingTime = cookingTime;
    }

    public String getName() {
        return name;
    }

    public double getCookingTime() {return cookingTime;}

    public double getPrice() {
        return price;
    }
}
