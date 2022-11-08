package fr.unice.polytech.cf;

import fr.unice.polytech.cf.ingredients.*;

import java.util.ArrayList;
import java.util.List;

public class Cookie {
    private final String name;
    private final double price;
    private double cookingTime;
    private Cooking cooking;
    private Dough dough;
    private IngredientTest doughTest;
    private Flavour flavour;
    private IngredientTest flavourTest;
    private Mix mix;
    private List<Topping> topping;
    private List<IngredientTest> toppingTest;

    public Cookie(String name) {
        this.name = name;
        this.price = 5;
    }

    public Cookie(String name, Cooking cooking, Dough dough, Flavour flavour, Mix mix, List<Topping> topping) {
        this.name = name;
        this.cooking = cooking;
        this.dough = dough;
        this.flavour = flavour;
        this.mix = mix;
        this.topping = new ArrayList<>(topping);
        this.price = this.dough.getPrice() + this.flavour.getPrice() + this.topping.stream().mapToDouble(Topping::getPrice).sum();
        this.cookingTime = 5;
    }

    public Cookie(String name, Cooking cooking, Dough dough, Flavour flavour, Mix mix, List<Topping> topping, int cookingTime) {
        this.name = name;
        this.cooking = cooking;
        this.dough = dough;
        this.flavour = flavour;
        this.mix = mix;
        this.topping = new ArrayList<>(topping);
        this.price = this.dough.getPrice() + this.flavour.getPrice() + this.topping.stream().mapToDouble(Topping::getPrice).sum();
        this.cookingTime = cookingTime;
    }

    public Cookie(String name, Cooking cooking, IngredientTest dough, IngredientTest flavour, Mix mix, List<IngredientTest> toppings){
        this.name = name;
        this.cooking = cooking;
        this.doughTest = dough;
        this.flavourTest = flavour;
        this.mix = mix;
        this.toppingTest = new ArrayList<>();
        for(IngredientTest t : toppings){
            this.toppingTest.add(t);
        }
        this.price = this.doughTest.getPrice() + this.flavourTest.getPrice() + this.toppingTest.stream().mapToDouble(IngredientTest::getPrice).sum();
        this.cookingTime = 5;
    }

    public String getName() {
        return name;
    }

    public double getCookingTime() {
        return cookingTime;
    }

    public double getPrice() {
        return price;
    }

    public Flavour getFlavour() {
        return flavour;
    }

    public Dough getDough() {
        return dough;
    }

    public List<Topping> getTopping() {
        return topping;
    }

    public List<Ingredient> getIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(dough);
        ingredients.add(flavour);
        ingredients.addAll(topping);
        return ingredients;
    }
}
