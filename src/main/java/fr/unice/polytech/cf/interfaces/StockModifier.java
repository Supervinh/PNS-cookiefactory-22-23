package fr.unice.polytech.cf.interfaces;

import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.exceptions.IngredientNotInStockException;

import java.util.List;
import java.util.UUID;

public interface StockModifier {

    void addIngredient(Ingredient ingredient);

    void addIngredients(List<Ingredient> ingredients);

    Ingredient removeIngredientByName(String ingredientName, UUID storeId) throws IngredientNotInStockException;
    List<Ingredient> removeIngredientsFromStock(List<Ingredient> ingredientsToRemove, UUID storeId);


}
