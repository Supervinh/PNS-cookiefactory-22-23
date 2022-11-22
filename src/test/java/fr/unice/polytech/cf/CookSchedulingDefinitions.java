package fr.unice.polytech.cf;

import fr.unice.polytech.cf.components.CartHandler;
import fr.unice.polytech.cf.components.Catalog;
import fr.unice.polytech.cf.entities.Order;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class CookSchedulingDefinitions {
    Store store;
    CookAccount cook;
    Catalog catalog;
    boolean isaccepted;

    @Given("the store is open")
    public void the_store_is_open() {
        store = new Store("store",LocalTime.of(8,0,0,0), LocalTime.of(17,0,0,0));
        catalog = new Catalog();
    }

    @Given("the cook has an empty schedule")
    public void the_cook_has_an_empty_schedule() {
        cook = new CookAccount("Gordon", LocalTime.of(8,0,0,0), LocalTime.of(17,0,0,0));
        store.getStoreSchedule().addcook(cook);
    }
    @When("is assigned a {int} minutes order")
    public void is_assigned_a_minutes_order(Integer int1) {
        CartHandler c = new CartHandler();
        c.addCookie(catalog.getCookie("chocolate"), 2);
        store.assignOrder(new Order(c, LocalDateTime.of(2022,11,19,15,15,0,0)));

    }
    @Then("he should have two slot taken for that order")
    public void he_should_have_two_slot_taken_for_that_order() {
        assert(cook.getCookSchedule().keySet().size() == 2);
    }

    @Given("a store with no cook available")
    public void a_store_with_no_cook_available() {}

    @Then("the order is refused")
    public void the_order_is_refused() {
        assert (!isaccepted);
    }

    @And("a cook is available and another isn't")
    public void a_cook_is_available_and_another_isn_t() {
        CookAccount cook1 = new CookAccount("Gordon", LocalTime.of(8,0,0,0), LocalTime.of(17,0,0,0));
        cook = new CookAccount("Ramsey", LocalTime.of(8,0,0,0), LocalTime.of(17,0,0,0));
        store.getStoreSchedule().addcook(cook1);
        store.getStoreSchedule().addcook(cook);
        store.assignOrder(new Order(new CartHandler(), LocalDateTime.of(2022,11,20,11,14,30,2))); // should occupy cook1
    }
    @And("a new order comes in")
    public void a_new_order_comes_in() {
        isaccepted = store.assignOrder(new Order(new CartHandler(), LocalDateTime.of(2022,11,20,11,14,30,2)));
    }
    @Then("the available cook should have the order assigned to him")
    public void the_available_cook_should_have_the_order_assigned_to_him() {
        assert (!cook.getCookSchedule().keySet().isEmpty());
    }

}
