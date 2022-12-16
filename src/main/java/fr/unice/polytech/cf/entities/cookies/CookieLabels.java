package fr.unice.polytech.cf.entities.cookies;

import fr.unice.polytech.cf.entities.ingredients.Cooking;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.entities.ingredients.Mix;

import java.util.List;

public class CookieLabels {
    String name;
    double price;
    double cookingTime;
    Cooking cooking ;
    Ingredient dough;
    Ingredient flavour ;
    Mix mix;
    List<Ingredient> topping;
}
