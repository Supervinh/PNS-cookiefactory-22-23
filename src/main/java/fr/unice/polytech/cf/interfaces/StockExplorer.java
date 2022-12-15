package fr.unice.polytech.cf.interfaces;

import fr.unice.polytech.cf.entities.ingredients.Ingredient;

import java.util.Optional;
import java.util.UUID;

public interface StockExplorer {

    Iterable<Ingredient> findAllIngredients();
    Optional<Ingredient> findIngredientById(UUID id);
    Optional<Ingredient> findIngredientByName(String name);
    Optional<Ingredient> findIngredientByStoreId(UUID storeId);
}
