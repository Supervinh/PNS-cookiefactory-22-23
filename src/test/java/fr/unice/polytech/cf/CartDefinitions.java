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

    @Given("the cart contains {int} cookies {word}")
    public  void theCartContainsThisCookies(int number, String name){
        cart = new Cart();
        try {cart.addCookie(new Cookie(name), number);}
        catch (RuntimeException ignored){}
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
            try {cart.addCookie(new Cookie(cookie), amount);}
            catch (RuntimeException ignored){}
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

    @Then("the cart should contain {int} cookies")
    public void the_cart_should_contain_cookies(Integer number) {
        assert (cart.getNbCookies()==number);
    }

    @Then("the cart should contain {int} cookies {word}")
    public void theCartShouldContainThisCookies(int number, String name){
       assert (cart.getCookies().get(name) == number);
    }

    @Then("the client should receive a purchase order")
    public void theClientShouldReceiveAPurchaseOrder() {
        assert(order!=null);
    }

    @Then("the client should get notified that the order is empty")
    public void theClientShouldGetNotifiedThatTheOrderIsEmpty() {
        assert(!ordered);
    }

    @Then("the cart should be empty")
    public void theCartIsEmpty(){assert (cart.getNbCookies() == 0);}
}
