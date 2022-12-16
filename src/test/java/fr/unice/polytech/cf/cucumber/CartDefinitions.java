package fr.unice.polytech.cf.cucumber;

import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.cf.entities.Item;
import fr.unice.polytech.cf.entities.Order;
import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.entities.cookies.CookieRecipe;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.entities.ingredients.IngredientEnum;
import fr.unice.polytech.cf.exceptions.*;
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
import java.util.Set;

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

    private boolean ordered;
    private Order order;
    private Customer customer;
    private Store store;
    private Item item;


    @Before
    public void settingUpContext() {
        ingredientRepository.deleteAll();
        customerRepository.deleteAll();
        storeRepository.deleteAll();
    }

    @Given("the cart contains {int} cookies {word}")
    public void theCartContainsThisCookies(int number, String name) throws AlreadyExistingCustomerException, IngredientNotInStockException {
        customer = customerRegistration.register("John", "Doe", "John@Doe.com");
        store = storeModifier.addStore("Store", LocalTime.of(8, 0, 0, 0), LocalTime.of(17, 0, 0, 0));
        for (int i = 0; i <= number; i++) {
            stockModifier.addIngredient(new Ingredient(store.getId(), IngredientEnum.DOUGH, "dough", 1));
            stockModifier.addIngredient(new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "flavour", 1));
        }

        if (number != 0) {
            item = new Item(new CookieRecipe(name, 1, 1,
                    stockExplorer.findIngredientByName("dough").get(0),
                    stockExplorer.findIngredientByName("flavour").get(0), new ArrayList<>()), number);
            cartModifier.addCookie(customer, store, item);
        }

    }


    @And("the cookie {word} price is {double}")
    public void theCookiePriceIs(String name, double price) {
        assert catalogExplorer.getCookie(name).getPrice() == price;
        //assert catalog.getCookie(name).getPrice() == price;
    }

    @And("the cookie {word} cooking time is {int}")
    public void theCookieTimeIs(String name, int time) {
        assert catalogExplorer.getCookie(name).getCookingTime() == time;
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
            item = new Item(new CookieRecipe(name, 1, 1,
                    stockExplorer.findIngredientByName("dough").get(0),
                    stockExplorer.findIngredientByName("flavour").get(0), new ArrayList<>()), number);
            cartModifier.addCookie(customer, store, item);
        }

    }

    @When("the client confirm the order")
    public void theClientConfirmTheOrder() throws EmptyCartException, PaymentException, OrderCancelledTwiceException, CloneNotSupportedException {
        order = cartProcessor.confirmOrder(customer, store, null);
        ordered = true;
        /*try {
            ordered = true;
            order = cartHandler.confirmOrder(isVIP);
        } catch (EmptyCartException e) {
            ordered = false;
        }*/
    }

    @Then("the cart should contain {int} cookies")
    public void the_cart_should_contain_cookies(Integer number) {
        assert (customer.getCart().stream().mapToInt(Item::getQuantity).sum() == number);
        //assert (cartHandler.getNbCookies() == number);
    }

    @Then("the cart should contain {int} cookies {word}")
    public void theCartShouldContainThisCookies(int number, String name) {
        Set<Item> items = customer.getCart();
        for (Item item : items) {
            System.out.println(item.getCookie().getName());
            System.out.println(item.getQuantity());
        }
        items.forEach(item -> {
            assert !item.getCookie().getName().equals(name) || item.getQuantity() == number;
        });
        //System.out.println("init cart" + cartHandler.getCookies().values());
        //assert !catalog.hasCookie(name) || (cartHandler.getCookies().get(catalog.getCookie(name)) == number);
    }

    @Then("the client should receive a purchase order")
    public void theClientShouldReceiveAPurchaseOrder() {
        assert (order != null);
    }

    @Then("the client should get notified that the order is empty")
    public void theClientShouldGetNotifiedThatTheOrderIsEmpty() {
        assert (!ordered);
    }

    @Then("the cart should be empty")
    public void theCartIsEmpty() {
        assert (customer.getCart().isEmpty());
        //assert (cartHandler.getNbCookies() == 0);
    }

    @Then("the cart's price should be {double}")
    public void thePriceShouldBe(double price) {
        assert (customer.getCart().stream().mapToDouble(item -> item.getQuantity() * item.getCookie().getPrice()).sum() == price);
        //assert cartHandler.getPrice() == price;
    }

    @Then("the cart's cooking time should be {double}")
    public void theCookingTimeshouldBe(double time) {
        assert (customer.getCart().stream().mapToDouble(item -> item.getQuantity() * item.getCookie().getCookingTime()).sum() == time);
        //System.out.println(cartHandler.getCookingTime());
        //assert cartHandler.getCookingTime() == time;
    }

    @Then("the price should be {double}")
    public void thePriceShouldBe(int price) {
        assert order.getPrice() == price;
    }
}
