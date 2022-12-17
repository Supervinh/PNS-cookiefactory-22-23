package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.exceptions.IngredientNotInStockException;
import fr.unice.polytech.cf.interfaces.explorer.StockExplorer;
import fr.unice.polytech.cf.interfaces.modifier.StockModifier;
import fr.unice.polytech.cf.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Component
public class StockHandler implements StockModifier, StockExplorer {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public StockHandler(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Iterable<Ingredient> findAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        if (findIngredientById(ingredient.getId()).isPresent()) {
            throw new RuntimeException("Ingredient already exists");
        }
        ingredientRepository.save(ingredient, ingredient.getId());
    }

    @Override
    public void addIngredients(List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            addIngredient(ingredient);
        }
    }

    @Override
    public Ingredient removeIngredientByName(String ingredientName, UUID storeId) throws IngredientNotInStockException {
        Optional<Ingredient> ingredientToRemove = findIngredientByStoreId(storeId).stream()
                .filter(ingredient -> ingredient.getName().equals(ingredientName))
                .findAny();
        if (ingredientToRemove.isEmpty()) {
            throw new IngredientNotInStockException(ingredientName);
        }
        ingredientRepository.deleteById(ingredientToRemove.get().getId());
        return ingredientToRemove.get();
    }

    @Override
    public List<Ingredient> removeIngredientsFromStock(List<Ingredient> ingredientsToRemove, UUID storeId) {
        List<Ingredient> removedIngredients = new ArrayList<>();
        if (ingredientsCanBeRemovedFromStock(ingredientsToRemove, storeId)) {
            for (Ingredient ingredient : ingredientsToRemove) {
                try {
                    removedIngredients.add(removeIngredientByName(ingredient.getName(), storeId));
                } catch (IngredientNotInStockException e) {
                    e.printStackTrace();
                }
            }
        }
        return removedIngredients;
    }

    @Override
    public boolean ingredientsCanBeRemovedFromStock(List<Ingredient> ingredientsToRemove, UUID storeId) {
        List<Ingredient> removedIngredients = new ArrayList<>();
        Ingredient removedIngredient;
        for (Ingredient ingredient : ingredientsToRemove) {
            try {
                removedIngredient = removeIngredientByName(ingredient.getName(), storeId);
            } catch (IngredientNotInStockException e) {
                e.printStackTrace();
                for (Ingredient ingredientToReAdd : removedIngredients) {
                    addIngredient(ingredientToReAdd);
                }
                return false;
            }
            removedIngredients.add(removedIngredient);
        }
        for (Ingredient ingredientToReAdd : removedIngredients) {
            addIngredient(ingredientToReAdd);
        }
        return true;
    }


    @Override
    public Optional<Ingredient> findIngredientById(UUID id) {
        return StreamSupport.stream(ingredientRepository.findAll().spliterator(), false)
                .filter(i -> id.equals(i.getId())).findAny();
    }

    @Override
    public List<Ingredient> findIngredientByName(String name) {
        return StreamSupport.stream(ingredientRepository.findAll().spliterator(), false)
                .filter(i -> name.equals(i.getName())).toList();
    }

    @Override
    public List<Ingredient> findIngredientByStoreId(UUID storeId) {
        return StreamSupport.stream(ingredientRepository.findAll().spliterator(), false)
                .filter(i -> storeId.equals(i.getStoreId())).toList();
    }
}
