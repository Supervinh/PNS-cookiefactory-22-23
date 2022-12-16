package fr.unice.polytech.cf.cucumber;

import fr.unice.polytech.cf.components.CookScheduler;
import fr.unice.polytech.cf.components.CustomerRegistry;
import fr.unice.polytech.cf.components.Kitchen;
import fr.unice.polytech.cf.entities.*;
import fr.unice.polytech.cf.entities.OrderState;
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
import fr.unice.polytech.cf.repositories.CookRepository;
import fr.unice.polytech.cf.repositories.CustomerRepository;
import fr.unice.polytech.cf.repositories.OrderRepository;
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
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;

public class CookSchedulingDefinitions {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreModifier storeModifier;
    @Autowired
    private CustomerRegistry customerRegistration;
    @Autowired
    private CookScheduler cookScheduler;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CookRepository cookRepository;
    @Autowired
    private Kitchen kitchen;
    private Store store;

    @Before
    public void settingUpContext() throws AlreadyExistingCustomerException {
        storeRepository.deleteAll();
        customerRepository.deleteAll();
        cookRepository.deleteAll();
        store = new Store("myCucumberStore", LocalTime.of(8, 0), LocalTime.of(20, 0));
        customerRegistration.register("John", "Doe", "John@Doe.com");
        cookScheduler.addCook("myCucumberCook", store.getOpeningTime(), store.getClosingTime(), store.getId());
        System.out.println(cookScheduler.getCooks());
        //storeRepository.save(store, store.getId());
    }

    @Given("the store is open")
    public void the_store_is_open() {
        storeModifier.changeStoreOpeningTime(store, LocalTime.of(8, 0));
        storeModifier.changeStoreClosingTime(store, LocalTime.of(8, 0));
    }

    @Given("the cook has an empty schedule")
    public void the_cook_has_an_empty_schedule() {
        System.out.println("Empty schedule");
        System.out.println(cookScheduler.getCooks());

        cookScheduler.getCooks().iterator().next().getCookSchedule().clear();
    }

    @When("is assigned a {int} minutes order")
    public void is_assigned_a_minutes_order(Integer cookingTime) throws EmptyCartException, PaymentException, OrderCancelledTwiceException, CloneNotSupportedException {
        Optional<Customer> customer = customerRegistration.findByName("John");
        Store store = storeRepository.findAll().iterator().next();
        if (customer.isPresent()) {
            BasicCookie cookie = new BasicCookie("myCucumberCookie", Cooking.CHEWY, new Ingredient(store.getId(), IngredientEnum.DOUGH, "dough",1), new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "flavor",1), Mix.MIXED, new ArrayList<>());
            cookie.setCookingTime(cookingTime);
            customer.get().getCart().add(new Item(cookie, 1));
            Order order = new Order(customer.get(),customer.get().getCart(),store.getId(), LocalDateTime.now());
            orderRepository.save(order, order.getId());
        }
        Order order = orderRepository.findAll().iterator().next();
        kitchen.assignOrder(order, store);


    }

    @Then("he should have two slot taken for that order")
    public void he_should_have_two_slot_taken_for_that_order() {
        assert (cookScheduler.getCooks().iterator().next().getCookSchedule().size() == 2);
    }

    @Given("a store with no cook available")
    public void a_store_with_no_cook_available() {
    }

    @Then("the order is refused")
    public void the_order_is_refused() {
        assert (orderRepository.findAll().iterator().next().getOrderState() != OrderState.WORKING_ON_IT);
    }

    /*@And("a cook is available and another isn't")
    public void a_cook_is_available_and_another_isn_t() throws AlreadyExistingCustomerException {
        //Cook cook1 = new Cook("Gordon", LocalTime.of(8, 0, 0, 0), LocalTime.of(17, 0, 0, 0));
        //cook = new Cook("Ramsey", LocalTime.of(8, 0, 0, 0), LocalTime.of(17, 0, 0, 0));
        Cook cook1 = cookRegistration.addCook("Gordon", LocalTime.of(8, 0, 0, 0), LocalTime.of(17, 0, 0, 0), store.getId());
        order = new Order(customer, customer.getCart(),store.getId(), LocalDateTime.of(2022, 11, 20, 11, 14, 30, 2));

        //store.getStoreSchedule().addCook(cook1);
        //store.getStoreSchedule().addCook(cook);
        kitchen.assignOrder(order, store);
        //store.assignOrder(new Order(new CartHandler(), LocalDateTime.of(2022, 11, 20, 11, 14, 30, 2))); // should occupy cook1
    }*/

    @And("a new order comes in")
    public void a_new_order_comes_in() {
        Optional<Customer> customer = customerRegistration.findByName("John");
        Store store = storeRepository.findAll().iterator().next();
        if (customer.isPresent()) {
            BasicCookie cookie = new BasicCookie("myCucumberCookie", Cooking.CHEWY, new Ingredient(store.getId(), IngredientEnum.DOUGH, "dough",1), new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "flavor",1), Mix.MIXED, new ArrayList<>());
            cookie.setCookingTime(0);
            customer.get().getCart().add(new Item(cookie, 1));
            Order order = new Order(customer.get(),customer.get().getCart(),store.getId(), LocalDateTime.now());
            orderRepository.save(order, order.getId());
        }
        Order order = orderRepository.findAll().iterator().next();
        kitchen.assignOrder(order, store);
    }

    @Then("the available cook should have the order assigned to him")
    public void the_available_cook_should_have_the_order_assigned_to_him() {
        assert (!cookScheduler.getCooks().iterator().next().getCookSchedule().keySet().isEmpty());
    }

}
