package fr.unice.polytech.cf;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

public class OrderDefinitions {
    Cart cart ;

    @Given("the cart contains {int} cookies")
    public void the_cart_contains_cookies(Integer number) {
        List<Cookie> cartCookies = new ArrayList<>();
        for (int i = 0; i<number; i++)
            cartCookies.add(new Cookie( 1));
        cart = new Cart(cartCookies);
    }

    @When("the client add {int} cookie\\(s) to the cart")
    public void the_client_add_cookie_s_to_the_cart(Integer cookies) {
        for (int i = 0; i<cookies; i++)
            cart.addCookie(new Cookie( 1));
    }

    @Then("the cart should contain {int} cookies")
    public void the_cart_should_contain_cookies(Integer number) {
        assert(cart.getCookies().size() == number);
    }

}
