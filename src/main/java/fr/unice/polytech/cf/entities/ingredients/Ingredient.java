package fr.unice.polytech.cf.entities.ingredients;

import java.util.Objects;
import java.util.UUID;

public class Ingredient {

    private UUID id;

    private UUID storeId;
    private final IngredientEnum type;
    private final String name;
    private double price;

    private double storeTax;

    public Ingredient(UUID storeId, IngredientEnum type, String name, double price) {
        this.storeId = storeId;
        this.type = type;
        this.name = name;
        this.price = price;
        this.storeTax = 0.0;
        this.id = UUID.randomUUID();
    }

    public Ingredient(UUID storeId, IngredientEnum type, String name, double price, double storeTax) {
        this.storeId = storeId;
        this.type = type;
        this.name = name;
        this.price = price;
        this.storeTax = storeTax;
        this.id = UUID.randomUUID();
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

    public UUID getId() {
        return id;
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

    public UUID getStoreId() {
        return storeId;
    }

}
