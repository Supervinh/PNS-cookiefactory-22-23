package fr.unice.polytech.cf.cucumber;

import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.cf.entities.Item;
import fr.unice.polytech.cf.entities.Order;
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
import fr.unice.polytech.cf.interfaces.*;
import fr.unice.polytech.cf.repositories.CustomerRepository;
import fr.unice.polytech.cf.repositories.IngredientRepository;
import fr.unice.polytech.cf.repositories.StoreRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartDefinitions {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CartModifier cartModifier;

    @Autowired
    private CustomerRegistration customerRegistration;

    @Autowired
    private CatalogExplorer catalogExplorer;

    @Autowired
    private CatalogModifier catalogModifier;

    @Autowired
    private StoreModifier storeModifier;

    @Autowired
    private CartProcessor cartProcessor;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private StockExplorer stockExplorer;

    @Autowired
    private StockModifier stockModifier;

    private Order order;
    private Customer customer;
    private Store store;
    private Item item;
    private HashSet<Item> items;
    private EmptyCartException exception;


    @Before
    public void settingUpContext() throws AlreadyExistingCustomerException {
        ingredientRepository.deleteAll();
        customerRepository.deleteAll();
        storeRepository.deleteAll();
        items = new HashSet<>();
        customer = customerRegistration.register("John", "Doe", "John@Doe.com");
        store = storeModifier.addStore("Store", LocalTime.of(8, 0, 0, 0), LocalTime.of(17, 0, 0, 0));
        for (int i = 0; i <= 10; i++) {
            stockModifier.addIngredient(new Ingredient(store.getId(), IngredientEnum.DOUGH, "dough", 2.5));
            stockModifier.addIngredient(new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "flavour", 3));
        }
    }

    @Given("the cart contains {int} cookies {word}")
    public void theCartContainsThisCookies(int number, String name) {
        if (number != 0) {
            item = new Item(new BasicCookie(name, Cooking.CHEWY, stockExplorer.findIngredientByName("dough").get(0),
                    stockExplorer.findIngredientByName("flavour").get(0), Mix.MIXED, new ArrayList<>()), number);
            int newQuantity = item.getQuantity();
            Optional<Item> existing = items.stream().filter(e -> e.getCookie().getName().equals(item.getCookie().getName())).findFirst();
            if (existing.isPresent()) {
                newQuantity += existing.get().getQuantity();
            }
            existing.ifPresent(items::remove);
            items.add(new Item(item.getCookie(), newQuantity));

            cartModifier.addCookie(customer, store, item);
        }

    }



    @And("the client is VIP")
    public void theClientIsVIP() {
        customer.setIsVIP(true);
    }

    @And("the client isn't VIP")
    public void theClientIsntVIP() {
        customer.setIsVIP(false);
    }

    @When("the client add {int} {word} to the cart")
    public void the_client_add_cookie_s_to_the_cart(Integer number, String name) {
        if (number != 0) {
            item = new Item(new BasicCookie(name, Cooking.CHEWY, stockExplorer.findIngredientByName("dough").get(0),
                    stockExplorer.findIngredientByName("flavour").get(0), Mix.MIXED, new ArrayList<>()), number);
            int newQuantity = item.getQuantity();
            Optional<Item> existing = items.stream().filter(e -> e.getCookie().getName().equals(item.getCookie().getName())).findFirst();
            if (existing.isPresent()) {
                newQuantity += existing.get().getQuantity();
            }
            existing.ifPresent(items::remove);
            items.add(new Item(item.getCookie(), newQuantity));
            cartModifier.addCookie(customer, store, item);
        }

    }

    @When("the client confirm the order")
    public void theClientConfirmTheOrder() throws
            PaymentException, OrderCancelledTwiceException, CloneNotSupportedException {
        try {
            order = cartProcessor.confirmOrder(customer, store, null);
        } catch (EmptyCartException e) {
            exception = assertThrows(EmptyCartException.class, () -> cartProcessor.confirmOrder(customer, store, null));
        }
    }

    @Then("the cart should contain {int} cookies")
    public void the_cart_should_contain_cookies(Integer number) {
        assert (customer.getCart().stream().mapToInt(Item::getQuantity).sum() == number);
    }

    @Then("the cart should contain {int} cookies {word}")
    public void theCartShouldContainThisCookies(int number, String name) {
        Set<Item> items = customer.getCart();
        items.forEach(item -> {
            assert !item.getCookie().getName().equals(name) || item.getQuantity() == number;
        });
    }

    @Then("the client should receive a purchase order")
    public void theClientShouldReceiveAPurchaseOrder() {
        assert (order != null);
    }

    @Then("the client should get notified that the order is empty")
    public void theClientShouldGetNotifiedThatTheOrderIsEmpty() {
        String expectedMessage = "The cart is empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Then("the cart should be empty")
    public void theCartIsEmpty() {
        assert (customer.getCart().isEmpty());
        //assert (cartHandler.getNbCookies() == 0);
    }

    @Then("the cart's price should be {double} times the price of a {word} cookie")
    public void thePriceShouldBe(double quantity, String name) {
        Item itemTemp = items.stream().filter(item -> item.getCookie().getName().equals(name)).findFirst().get();
        assert (customer.getCart().stream().mapToDouble(item -> item.getQuantity() * item.getCookie().getPrice()).sum() == quantity * itemTemp.getCookie().getPrice());
    }

    @Then("the cart's price should be {double} times the price of a {word} cookie plus {double}" +
            " times the price of a {word} cookie")
    public void thePriceShouldBe(double quantity, String name, double quantity2, String name2) {
        Item itemTemp = items.stream().filter(item -> item.getCookie().getName().equals(name)).findFirst().get();
        Item itemTemp2 = items.stream().filter(item -> item.getCookie().getName().equals(name2)).findFirst().get();
        assert (customer.getCart().stream().mapToDouble(item -> item.getQuantity() * item.getCookie().getPrice()).sum()
                == quantity * itemTemp.getCookie().getPrice() + quantity2 * itemTemp2.getCookie().getPrice());
    }



}
