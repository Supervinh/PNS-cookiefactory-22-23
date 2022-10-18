package fr.unice.polytech.cf.ingredients;

public class Mix implements Ingredient {
    private String name;
    private double price;

    public Mix(String name, double price) {
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
