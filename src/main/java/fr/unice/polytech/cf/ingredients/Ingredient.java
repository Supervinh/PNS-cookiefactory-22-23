package fr.unice.polytech.cf.ingredients;

import java.util.Objects;

public class Ingredient {

    private final IngredientEnum type;
    private final String name;
    private double price;

    public Ingredient(IngredientEnum type, String name, double price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice(String ingredient) {
        return price;
    }

    public void addTaxe(double taxe) {
        this.price += taxe;
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
