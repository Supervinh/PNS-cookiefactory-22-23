package fr.unice.polytech.cf;

import fr.unice.polytech.cf.ingredients.*;

public class Cookie {
    private final String name;
    private final double price;
    private Cooking cooking;
    private Dough dough;
    private Flavour flavour;
    private Mix mix;
    private Topping topping;

    public Cookie(String name) {
        this.name = name;
        this.price = 5;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
