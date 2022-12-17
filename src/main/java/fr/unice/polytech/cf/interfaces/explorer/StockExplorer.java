package fr.unice.polytech.cf.interfaces.explorer;

import fr.unice.polytech.cf.entities.ingredients.Ingredient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StockExplorer {

    Iterable<Ingredient> findAllIngredients();

    Optional<Ingredient> findIngredientById(UUID id);

    List<Ingredient> findIngredientByName(String name);

    List<Ingredient> findIngredientByStoreId(UUID storeId);

    boolean ingredientsCanBeRemovedFromStock(List<Ingredient> ingredientsToRemove, UUID storeId);
}
