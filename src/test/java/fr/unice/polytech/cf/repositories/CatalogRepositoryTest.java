package fr.unice.polytech.cf.repositories;

import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.entities.cookies.Cookie;
import fr.unice.polytech.cf.entities.ingredients.Cooking;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.entities.ingredients.IngredientEnum;
import fr.unice.polytech.cf.entities.ingredients.Mix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CatalogRepositoryTest {

    @Autowired
    CatalogRepository catalogRepository;

    Cookie cookie;

    @BeforeEach
    void setup() {
        catalogRepository.deleteAll();
        cookie = new BasicCookie("cookie", Cooking.CHEWY,
                new Ingredient(UUID.randomUUID(), IngredientEnum.DOUGH, "dough", 1),
                new Ingredient(UUID.randomUUID(), IngredientEnum.FLAVOUR, "flavour", 1),
                Mix.MIXED, new ArrayList<>());
    }

    @Test
    void testSaveAndFind() {
        UUID genId = cookie.getId();
        assertNotNull(genId);
        catalogRepository.save(cookie, cookie.getId());
        Optional<Cookie> foundJohnOpt = catalogRepository.findById(genId);
        assertTrue(foundJohnOpt.isPresent());
        assertEquals(cookie, foundJohnOpt.get());
    }

    @Test
    void testDeleteAll() {
        assertEquals(0, catalogRepository.count());
        catalogRepository.save(cookie, cookie.getId());
        assertEquals(1, catalogRepository.count());
        catalogRepository.deleteAll();
        assertEquals(0, catalogRepository.count());
    }


}