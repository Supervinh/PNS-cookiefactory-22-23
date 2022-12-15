package fr.unice.polytech.cf.repositories;

import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.repositories.BasicRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Component
public class IngredientRepository extends BasicRepositoryImpl<Ingredient, UUID> {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientRepository(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Optional<Ingredient> findByStoreId(UUID storeId) {
        return StreamSupport.stream(ingredientRepository.findAll().spliterator(), false)
                .filter(i -> storeId.equals(i.getStoreId())).findAny();
    }
}
