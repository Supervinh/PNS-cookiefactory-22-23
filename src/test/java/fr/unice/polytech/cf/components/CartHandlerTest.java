package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.cf.entities.Item;
import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.entities.ingredients.Cooking;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.entities.ingredients.IngredientEnum;
import fr.unice.polytech.cf.entities.ingredients.Mix;
import fr.unice.polytech.cf.exceptions.AlreadyExistingCustomerException;
import fr.unice.polytech.cf.exceptions.EmptyCartException;
import fr.unice.polytech.cf.exceptions.OrderCancelledTwiceException;
import fr.unice.polytech.cf.exceptions.PaymentException;
import fr.unice.polytech.cf.interfaces.explorer.CustomerFinder;
import fr.unice.polytech.cf.interfaces.modifier.*;
import fr.unice.polytech.cf.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CartHandlerTest {
    @Autowired
    StoreModifier storemodifier;
    LocalTime time = LocalTime.of(8, 30);
    LocalTime time2 = LocalTime.of(20, 30);
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerRegistration customerRegistration;
    @Autowired
    private CustomerFinder customerFinder;
    @Autowired
    private CartModifier cartModifier;
    @Autowired
    private CartProcessor cartProcessor;
    private Customer john;
    private final Store store = new Store("store1", 0.1, time, time2);

    @Autowired
    private StockModifier stockModifier;


    @BeforeEach
    void setUp() throws AlreadyExistingCustomerException {
        store.setId(UUID.randomUUID());


        customerRepository.deleteAll();
        customerRegistration.register("John", "smith", "johnsmith@gmail.com");
        john = customerFinder.findByName("John").get();
        stockModifier.addIngredient(new Ingredient(store.getId(), IngredientEnum.DOUGH, "Chocolate", 3));
        stockModifier.addIngredient(new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "Cinnamon", 2.5));
        stockModifier.addIngredient(new Ingredient(store.getId(), IngredientEnum.DOUGH, "Chocolate", 3));
        stockModifier.addIngredient(new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "Cinnamon", 2.5));


    }


    @Test
    public void emptyCartByDefault() {
        assertEquals(0, john.getCart().size());
    }


    @Test
    public void addItemsAndCheckIfCorrect() throws EmptyCartException, PaymentException, OrderCancelledTwiceException, CloneNotSupportedException {
        Item firstItem = new Item(new BasicCookie("chocolate", Cooking.CRUNCHY,
                new Ingredient(store.getId(), IngredientEnum.DOUGH, "Chocolate", 3),
                new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>()), 2);
        cartModifier.addCookie(john, store, firstItem);
        Item secondItem = new Item(new BasicCookie("DARK_TEMPTATION", Cooking.CRUNCHY,
                new Ingredient(store.getId(), IngredientEnum.DOUGH, "Chocolate", 3),
                new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>()), 3);
        cartModifier.addCookie(john, store, secondItem);

        Set<Item> oracle = Set.of(firstItem, secondItem);
        assertEquals(oracle, john.getCart());
        //Ici on vérifie que si on tente de rajouter un cookie contenant
        //un ingredient pas en stock, alors le cookie n'est pas ajouté au
        //cart
        Item third = new Item(new BasicCookie("fruit", Cooking.CRUNCHY,
                new Ingredient(store.getId(), IngredientEnum.DOUGH, "fraise", 3),
                new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "poire", 2.5),
                Mix.MIXED, new ArrayList<>()), 2);
        cartModifier.addCookie(john, store, third);
        assertEquals(oracle, john.getCart());

        assertEquals(cartProcessor.getPrice(john, store), 27.5 * 1.1);
        assertEquals(cartProcessor.getCookingTime(john), 25);


    }


}
