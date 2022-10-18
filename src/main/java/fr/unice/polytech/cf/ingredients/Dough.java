package fr.unice.polytech.cf.ingredients;

public class Dough implements Ingredient {
    private String name;
    private double price;

    public Dough(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}

