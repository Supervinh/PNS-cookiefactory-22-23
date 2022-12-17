package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.entities.cookies.Cookie;
import fr.unice.polytech.cf.entities.ingredients.Cooking;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.entities.ingredients.IngredientEnum;
import fr.unice.polytech.cf.entities.ingredients.Mix;
import fr.unice.polytech.cf.exceptions.IngredientNotInStockException;
import fr.unice.polytech.cf.interfaces.modifier.StockModifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.catalog.Catalog;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@SpringBootTest
public class CatalogHandlerTest {
    @Autowired
    CatalogHandler catalog;
    @Autowired
    private StockModifier stockModifier;

    Set<Cookie> premade;
    LocalTime time= LocalTime.of(8,30);
    LocalTime time2= LocalTime.of(20,30);
    private Store store=new Store("store1",0.1,time,time2);
    @BeforeEach
    void setup(){

        store.setId(UUID.randomUUID());

        stockModifier.addIngredient(new Ingredient(store.getId(), IngredientEnum.DOUGH, "Chocolate", 3));
        stockModifier.addIngredient(new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "Cinnamon", 2.5));
        stockModifier.addIngredient(new Ingredient(store.getId(), IngredientEnum.DOUGH, "Chocolate", 3));
        stockModifier.addIngredient( new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "Cinnamon", 2.5));


    }
    @Test
    void listPreMadeRecipesTest() {
        catalog.addCookie(new BasicCookie("chocolate", Cooking.CRUNCHY,
                new Ingredient(store.getId(), IngredientEnum.DOUGH, "Chocolate", 3),
                new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>()));
        catalog.addCookie(new BasicCookie("DARK_TEMPTATION", Cooking.CRUNCHY,
                new Ingredient(store.getId(), IngredientEnum.DOUGH, "Chocolate", 3),
                new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>()));
        assertEquals(2, catalog.getCookies().size());
        assertEquals(new BasicCookie("chocolate", Cooking.CRUNCHY,
                new Ingredient(store.getId(), IngredientEnum.DOUGH, "Chocolate", 3),
                new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>()).getName(),catalog.getCookie("chocolate").getName());
        catalog.removeCookie(new BasicCookie("DARK_TEMPTATION", Cooking.CRUNCHY,
                new Ingredient(store.getId(), IngredientEnum.DOUGH, "Chocolate", 3),
                new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>()));



    }


}
