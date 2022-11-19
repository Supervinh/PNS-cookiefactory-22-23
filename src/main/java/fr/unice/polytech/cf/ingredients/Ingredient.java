package fr.unice.polytech.cf.ingredients;

import java.util.Objects;

public class Ingredient {

    private final IngredientEnum type;
    private final String name;
    private double price;

    private double storeTax;

    public Ingredient(IngredientEnum type, String name, double price) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.storeTax = 0.0;
    }

    public Ingredient(IngredientEnum type, String name, double price, double storeTax) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.storeTax = storeTax;
    }

    public double getBasePrice() {
        return price;
    }

    public void setUntaxedPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return Math.floor((price*(1 + storeTax) * 100.0)) / 100.0;
    }

    public void setStoreTax(double taxe) {
        this.storeTax = taxe;
    }

    public double getStoreTax() {
        return this.storeTax;
    }

    public String getName() {
        return name;
    }

    public IngredientEnum getType() {
        return type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
