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
import fr.unice.polytech.cf.interfaces.explorer.StoreFinder;
import fr.unice.polytech.cf.interfaces.modifier.StoreModifier;
import fr.unice.polytech.cf.repositories.CustomerRepository;
import fr.unice.polytech.cf.repositories.OrderRepository;
import fr.unice.polytech.cf.repositories.StoreRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

public class CookSchedulingDefinitions {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreModifier storeModifier;
    @Autowired
    private StoreFinder storeFinder;
    @Autowired
    private CustomerRegistry customerRegistration;
    @Autowired
    private CookScheduler cookScheduler;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private Kitchen kitchen;

    @Before
    public void settingUpContext() throws AlreadyExistingCustomerException {
        storeRepository.deleteAll();
        customerRepository.deleteAll();
        orderRepository.deleteAll();
        customerRegistration.register("John", "Doe", "John@Doe.com");
    }

    @Given("the store {word} is open")
    public void the_store_is_open(String storeName) throws AlreadyExistingCustomerException {
        storeModifier.addStore(storeName, LocalTime.of(0, 0), LocalTime.of(23, 59, 59));
    }

    @Given("the cook {word} is working in {word} with an empty schedule")
    public void the_cook_has_an_empty_schedule(String cookName, String storeName) throws AlreadyExistingCustomerException {
        Optional<Store> store = storeFinder.findByName(storeName);
        if (store.isPresent()) {
            Cook myNewCook = cookScheduler.addCook(cookName, store.get().getOpeningTime(), store.get().getClosingTime(), store.get().getId());
            myNewCook.getCookSchedule().clear();
        }
    }

    @When("a {int} minutes order comes in the {word} 's kitchen")
    public void a_minutes_order_comes_in_the_kitchen(Integer cookingTime, String storeName) {
        Optional<Customer> customer = customerRegistration.findByName("John");
        Optional<Store> store = storeFinder.findByName(storeName);
        if (customer.isPresent() && store.isPresent()) {
            BasicCookie cookie = new BasicCookie("myCucumberCookie", Cooking.CHEWY, new Ingredient(store.get().getId(), IngredientEnum.DOUGH, "dough",1), new Ingredient(store.get().getId(), IngredientEnum.FLAVOUR, "flavor",1), Mix.MIXED, new ArrayList<>());
            cookie.setCookingTime(cookingTime-15);
            customer.get().getCart().add(new Item(cookie, 1));
            Order order = new Order(customer.get(),customer.get().getCart(),store.get().getId(), LocalDateTime.now());
            System.out.println(order.getCookingTime());
            System.out.println(order.getRetrieveDate());
            orderRepository.save(order, order.getId());
            kitchen.assignOrder(order, store.get());
        }
    }

    @Then("{word} should have {int} slot taken for that order")
    public void he_should_have_two_slot_taken_for_that_order(String cookName, int nbSlots) {
        Optional<Cook> workingCook = cookScheduler.findByName(cookName);
        assert workingCook.isEmpty() || workingCook.get().getCookSchedule().size() == nbSlots;
    }

    @Given("a store with no cook available")
    public void a_store_with_no_cook_available() {
    }

    @Then("the order is refused")
    public void the_order_is_refused() {
        Iterable<Order> orders = orderRepository.findAll();
        for (Order order : orders)
            assert (order.getOrderState() != OrderState.WORKING_ON_IT);
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
