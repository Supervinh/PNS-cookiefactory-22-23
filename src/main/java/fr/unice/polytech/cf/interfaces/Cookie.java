package fr.unice.polytech.cf.interfaces;

import fr.unice.polytech.cf.entities.Item;
import fr.unice.polytech.cf.entities.ingredients.Cooking;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.entities.ingredients.Mix;

import java.util.List;

public interface Cookie {
    String getName();
    double getCookingTime();
    double getPrice();
    Cooking getCooking();
    Ingredient getDough();
    Ingredient getFlavour();
    Mix getMix();
    List<Ingredient> getTopping();

    List<Ingredient> getIngredients();
}

