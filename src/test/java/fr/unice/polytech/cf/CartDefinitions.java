package fr.unice.polytech.cf;

import fr.unice.polytech.cf.exceptions.EmptyCartException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartDefinitions {
    Cart cart ;
    Catalog catalog;
    boolean possible;
    boolean ordered;
    Order order;

    @Given("the cart contains {int} cookies")
    public void theCartContainsCookies(int number) {
        cart = new Cart();
        if(number>0){
            cart.addCookie(new Cookie("chocolat"), number);
        }
    }

    @And("the catalog contains the cookie {word}")
    public void the_catalog_contains_cookie(String cookie) {
        catalog = new Catalog();
        if (!catalog.hasCookie(cookie))
            catalog.addCookie(new Cookie("chocolate"));
        possible = true;
        assert(catalog.hasCookie(cookie));
    }

    @When("the client add {int} {word} to the cart")
    public void the_client_add_cookie_s_to_the_cart(Integer amount, String cookie) {
        if(possible){
            cart.addCookie(new Cookie( cookie), amount);
        }
    }

    @Then("the cart should contain {int} cookies")
    public void the_cart_should_contain_cookies(Integer number) {
        assert (cart.getNbCookies()==number);
    }

    @When("the client confirm the order")
    public void theClientConfirmTheOrder() throws CloneNotSupportedException {
        try {
            ordered = true;
            order = cart.confirmOrder();
        }catch (EmptyCartException e){
            ordered = false;
        }
    }

    @Then("the client should receive a purchase order")
    public void theClientShouldReceiveAPurchaseOrder() {
        assert(order!=null);
    }

    @Then("the client should get notified that the order is empty")
    public void theClientShouldGetNotifiedThatTheOrderIsEmpty() {
        assert(!ordered);
    }
}
