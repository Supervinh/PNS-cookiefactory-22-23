package fr.unice.polytech.cf;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OrderDefinitions {
    Cart cart ;
    Catalog catalog;

    /*@Given("the catalog contains the cookie {String}")
    public void the_catalog_contains_cookie(String cookie) {
        if (catalog.hasCookie(cookie)) {
            cart.addCookie(new Cookie( cookie));
        }

    }*/

    @Given("the cart contains {int} cookies")
    public void theCartContainsCookies(int number) {
        cart = new Cart();
        for (int i = 0; i<number; i++)
            cart.addCookie(new Cookie("chocolat"));
    }

    @When("the client add {int} {word} to the cart")
    public void the_client_add_cookie_s_to_the_cart(Integer amount, String cookie) {
        for (int i = 0; i<amount; i++)
            cart.addCookie(new Cookie( cookie));
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


}
