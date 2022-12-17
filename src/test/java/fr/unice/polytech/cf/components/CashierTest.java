package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.cf.entities.Item;
import fr.unice.polytech.cf.entities.Order;
import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.entities.ingredients.Cooking;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.entities.ingredients.IngredientEnum;
import fr.unice.polytech.cf.entities.ingredients.Mix;
import fr.unice.polytech.cf.exceptions.OrderCancelledTwiceException;
import fr.unice.polytech.cf.exceptions.PaymentException;
import fr.unice.polytech.cf.interfaces.modifier.Bank;
import fr.unice.polytech.cf.interfaces.modifier.Payment;
import fr.unice.polytech.cf.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CashierTest {

    @Autowired
    CustomerRepository customerRepository;
    Customer customer1;
    Customer customer2;
    LocalTime time = LocalTime.of(8, 30);
    LocalTime time2 = LocalTime.of(20, 30);
    @Autowired
    private Payment cashier;
    @Autowired
    private Bank bank;
    private Store store;
    private Set<Item> items;

    @BeforeEach
    public void setUp() throws Exception {
        customerRepository.deleteAll();
        store = new Store("store1", 0.1, time, time2);

        // We could also use below the customerRegistry component to setup the test environment
        items = new HashSet<>();
        items.add(new Item(new BasicCookie("chocolate", Cooking.CRUNCHY,
                new Ingredient(store.getId(), IngredientEnum.DOUGH, "Chocolate", 3),
                new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>()), 3));
        items.add(new Item(new BasicCookie("DARK_TEMPTATION", Cooking.CRUNCHY,
                new Ingredient(store.getId(), IngredientEnum.DOUGH, "Chocolate", 3),
                new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>()), 1));
        // Customers
        customer1 = new Customer("paul", "edouard", "pauledouard@hotmail.com");
        customerRepository.save(customer1, customer1.getId());
        customer2 = new Customer("jean", "dameso", "jean.dameso@gmail.com");
        customerRepository.save(customer2, customer2.getId());
    }

    @Test
    public void payement() throws OrderCancelledTwiceException, PaymentException {
        // paying order
        Order order = cashier.payOrder(customer1, items, store, LocalDateTime.of(2022, 12, 16, 16, 58));
        assertNotNull(order);

        assertEquals(items, order.getItems());
        double price = (3 * new BasicCookie("chocolate", Cooking.CRUNCHY,
                new Ingredient(store.getId(), IngredientEnum.DOUGH, "Chocolate", 3),
                new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>()).getPrice()) + (1 * new BasicCookie("DARK_TEMPTATION", Cooking.CRUNCHY,
                new Ingredient(store.getId(), IngredientEnum.DOUGH, "Chocolate", 3),
                new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>()).getPrice());
        assertEquals(1.1 * price, order.getPrice(), 0.000001);
        assertEquals(2, order.getItems().size());

    }


}
