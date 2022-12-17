package fr.unice.polytech.cf.entities.cookies;

import fr.unice.polytech.cf.entities.ingredients.Ingredient;

import java.util.List;
import java.util.UUID;

public interface Cookie {

    String getName();

    double getCookingTime();

    void setCookingTime(int cookingTime);

    double getPrice();

    void setPrice(double price);

    Ingredient getDough();

    Ingredient getFlavour();

    List<Ingredient> getTopping();

    List<Ingredient> getIngredients();

    UUID getId();
}


