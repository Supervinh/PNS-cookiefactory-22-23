package fr.unice.polytech.cf;

import fr.unice.polytech.cf.Exceptions.EmptyCartException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OrderDefinitions {
    Cart cart ;
    Catalog catalog;

    boolean possible;

    boolean ordered;

    Order order;

    @And("the catalog contains the cookie {word}")
    public void the_catalog_contains_cookie(String cookie) {
        catalog = new Catalog();
        if (!catalog.hasCookie(cookie))
            catalog.addCookie(new Cookie("chocolate"));
        possible = true;
        assert(catalog.hasCookie(cookie));
    }

    @And("the catalog does not contains the cookie {word}")
    public void the_catalog_does_not_contains_cookie(String cookie) {
        catalog = new Catalog();
        possible = false;
        assert(!catalog.hasCookie(cookie));

    }

    @Given("the cart contains {int} cookies")
    public void theCartContainsCookies(int number) {
        cart = new Cart();
        for (int i = 0; i<number; i++)
            cart.addCookie(new Cookie("chocolat"));
    }

    @When("the client add {int} {word} to the cart")
    public void the_client_add_cookie_s_to_the_cart(Integer amount, String cookie) {
        if(possible){
            for (int i = 0; i<amount; i++)
                cart.addCookie(new Cookie( cookie));
        }
    }

    @Then("the cart should contain {int} cookies")
    public void the_cart_should_contain_cookies(Integer number) {
        assert(cart.getCookies().size() == number);
    }

    @Given("the cart doesn't contain the cookie {string}")
    public void the_catalog_doesn_t_contain_cookie(String cookie) {
        if (!catalog.hasCookie(cookie)) {
            System.out.println("the catalog doesn't contain the cookie");
        }
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

    @Then("the client should get notified that the order is empty")
    public void theClientShouldGetNotifiedThatTheOrderIsEmpty() {
        assert(!ordered);
    }

    @Then("the client should receive a purchase order")
    public void theClientShouldReceiveAPurchaseOrder() {
        assert(order!=null);
    }
}
