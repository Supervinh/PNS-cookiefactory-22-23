package fr.unice.polytech.cf.entities.cookies;

import fr.unice.polytech.cf.entities.ingredients.Cooking;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.entities.ingredients.Mix;

import java.util.ArrayList;
import java.util.List;

public interface Cookie {

    public String getName();

    public double getCookingTime();

    public double getPrice();

    public void setPrice(double price);

    public void setCookingTime(int cookingTime);

    public Ingredient getDough();

    public Ingredient getFlavour();

    public List<Ingredient> getTopping();

    public List<Ingredient> getIngredients();
}


