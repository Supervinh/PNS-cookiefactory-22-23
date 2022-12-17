package fr.unice.polytech.cf.repositories;

import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.entities.ingredients.IngredientEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IngredientRepositoryTest {

    @Autowired
    IngredientRepository ingredientRepository;

    Ingredient ingredient;

    @BeforeEach
    void setup() {
        ingredientRepository.deleteAll();
        ingredient = new Ingredient(UUID.randomUUID(), IngredientEnum.DOUGH, "dough", 1);
    }

    @Test
    void testSaveAndFind() {
        UUID genId = ingredient.getId();
        assertNotNull(genId);
        ingredientRepository.save(ingredient, ingredient.getId());
        Optional<Ingredient> foundJohnOpt = ingredientRepository.findById(genId);
        assertTrue(foundJohnOpt.isPresent());
        assertEquals(ingredient, foundJohnOpt.get());
    }

    @Test
    void testDeleteAll() {
        assertEquals(0, ingredientRepository.count());
        ingredientRepository.save(ingredient, ingredient.getId());
        assertEquals(1, ingredientRepository.count());
        ingredientRepository.deleteAll();
        assertEquals(0, ingredientRepository.count());
    }


}