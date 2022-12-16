package fr.unice.polytech.cf.cucumber;

import fr.unice.polytech.cf.components.CartHandler;
import fr.unice.polytech.cf.components.Kitchen;
import fr.unice.polytech.cf.entities.*;
import fr.unice.polytech.cf.entities.cookies.CookieRecipe;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.entities.ingredients.IngredientEnum;
import fr.unice.polytech.cf.exceptions.AlreadyExistingCustomerException;
import fr.unice.polytech.cf.exceptions.EmptyCartException;
import fr.unice.polytech.cf.exceptions.OrderCancelledTwiceException;
import fr.unice.polytech.cf.exceptions.PaymentException;
import fr.unice.polytech.cf.interfaces.*;
import fr.unice.polytech.cf.repositories.CookRepository;
import fr.unice.polytech.cf.repositories.CustomerRepository;
import fr.unice.polytech.cf.repositories.StoreRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class CookSchedulingDefinitions {
    @Autowired
    private CatalogModifier catalogModifier;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreModifier storeModifier;
    @Autowired
    private CookRepository cookRepository;
    @Autowired
    private CookRegistration cookRegistration;
    @Autowired
    private CatalogExplorer catalogExplorer;

    @Autowired
    private CartModifier cartModifier;
    @Autowired
    private Kitchen kitchen;
    @Autowired
    private CartProcessor cartProcessor;



    private Store store;
    private Cook cook;
    private boolean isaccepted;
    private Customer customer;
    private Item item;
    private Order order;


    @Before
    public void settingUpContext() {
        storeRepository.deleteAll();
        catalogModifier.addCookie(new CookieRecipe("chocolate", 1, 1,
                new Ingredient(store.getId(), IngredientEnum.DOUGH, "dough", 1),
                new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "flavour", 1), new ArrayList<>()));
        customer = new Customer("John", "Doe", "John@Doe.com");
        item = new Item(catalogExplorer.getCookie("chocolate"), 2);
    }

    @Given("the store is open")
    public void the_store_is_open() {
        store = storeModifier.addStore("store", LocalTime.of(8, 0, 0, 0),
                LocalTime.of(17, 0, 0, 0));
        //store = new Store("store",LocalTime.of(8,0,0,0), LocalTime.of(17,0,0,0));
        //catalogHandler = new CatalogHandler();
    }

    @Given("the cook has an empty schedule")
    public void the_cook_has_an_empty_schedule() throws AlreadyExistingCustomerException {
        cook = cookRegistration.addCook("Cook", LocalTime.of(8, 0, 0, 0), LocalTime.of(17, 0, 0, 0), store.getId());

        //cook = new Cook("Gordon", LocalTime.of(8, 0, 0, 0), LocalTime.of(17, 0, 0, 0));
        //store.getStoreSchedule().addCook(cook);
    }

    @When("is assigned a {int} minutes order")
    public void is_assigned_a_minutes_order(Integer int1) throws EmptyCartException, PaymentException, OrderCancelledTwiceException, CloneNotSupportedException {
        //CartHandler c = new CartHandler();
        cartModifier.addCookie(customer, store, item);
        //c.addCookie(catalogModifier.getCookie("chocolate"), 2);
        //order = cartProcessor.confirmOrder(customer, store);
        order = new Order(customer, customer.getCart(),store.getId(), LocalDateTime.of(2022, 11, 19, 15, 15, 0, 0));
        kitchen.assignOrder(order, store);
        //store.assignOrder(new Order(c, LocalDateTime.of(2022, 11, 19, 15, 15, 0, 0)));

    }

    @Then("he should have two slot taken for that order")
    public void he_should_have_two_slot_taken_for_that_order() {
        assert (cook.getCookSchedule().keySet().size() == 2);
    }

    @Given("a store with no cook available")
    public void a_store_with_no_cook_available() {
    }

    @Then("the order is refused")
    public void the_order_is_refused() {
        assert (!isaccepted);
    }

    @And("a cook is available and another isn't")
    public void a_cook_is_available_and_another_isn_t() throws AlreadyExistingCustomerException {
        //Cook cook1 = new Cook("Gordon", LocalTime.of(8, 0, 0, 0), LocalTime.of(17, 0, 0, 0));
        //cook = new Cook("Ramsey", LocalTime.of(8, 0, 0, 0), LocalTime.of(17, 0, 0, 0));
        Cook cook1 = cookRegistration.addCook("Gordon", LocalTime.of(8, 0, 0, 0), LocalTime.of(17, 0, 0, 0), store.getId());
        order = new Order(customer, customer.getCart(),store.getId(), LocalDateTime.of(2022, 11, 20, 11, 14, 30, 2));

        //store.getStoreSchedule().addCook(cook1);
        //store.getStoreSchedule().addCook(cook);
        kitchen.assignOrder(order, store);
        //store.assignOrder(new Order(new CartHandler(), LocalDateTime.of(2022, 11, 20, 11, 14, 30, 2))); // should occupy cook1
    }

    @And("a new order comes in")
    public void a_new_order_comes_in() {
        order = new Order(customer, customer.getCart(),store.getId(), LocalDateTime.of(2022, 11, 20, 11, 14, 30, 2));
        isaccepted = kitchen.assignOrder(order, store);
    }

    @Then("the available cook should have the order assigned to him")
    public void the_available_cook_should_have_the_order_assigned_to_him() {
        assert (!cook.getCookSchedule().keySet().isEmpty());
    }

}
