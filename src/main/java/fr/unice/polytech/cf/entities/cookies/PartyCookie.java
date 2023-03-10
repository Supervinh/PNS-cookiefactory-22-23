package fr.unice.polytech.cf.entities.cookies;

import fr.unice.polytech.cf.entities.ingredients.Cooking;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.entities.ingredients.Mix;

import java.util.ArrayList;
import java.util.List;

public class PartyCookie extends BasicCookie {
    private final int multiplier;
    private final String occasion;
    private final String theme;

    public PartyCookie(String name, Cooking cooking, Ingredient dough, Ingredient flavour, Mix mix, List<Ingredient> toppings, int multiplier, String occasion, String theme) {
        super(name, cooking, dough, flavour, mix, toppings);
        this.multiplier = multiplier;
        setPrice((int) (multiplier * (this.getDough().getPrice() + this.getFlavour().getPrice() + this.getTopping().stream().mapToDouble(Ingredient::getPrice).sum())));
        this.occasion = occasion;
        this.theme = theme;
    }

    @Override
    public List<Ingredient> getIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < multiplier; i++) {
            ingredients.add(getDough());
            ingredients.add(getFlavour());
            for (Ingredient t : getTopping()) {
                ingredients.add(t);
            }
        }
        return ingredients;
    }

    @Override
    public double getPrice() {
        return Math.floor((super.getPrice() * (1 + 0.25) * 100.0)) / 100.0;
    }

    public String getOccasion() {
        return occasion;
    }

    public String getTheme() {
        return theme;
    }

}
