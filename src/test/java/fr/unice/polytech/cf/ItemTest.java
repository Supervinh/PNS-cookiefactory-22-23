package fr.unice.polytech.cf;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Only to make the difference with simple junit tests
 */
class ItemTest {

    @Test
    void getAmountTest() {
        Cookie i = new Cookie(new Ingredient("coco",1),10);
        assertEquals(10,i.getAmount());
    }

    @Test
    /**
     * testing the equality of ingredients
     */
    void getIngredientTest() {
        Cookie i = new Cookie(new Ingredient("coco",1),10);
        assertEquals(new Ingredient("coco",1),i.getIngredient());
    }
}