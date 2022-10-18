package fr.unice.polytech.cf.ingredients;

public class Cooking implements Ingredient {
    private String name;
    private double price;

    public Cooking(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

}
