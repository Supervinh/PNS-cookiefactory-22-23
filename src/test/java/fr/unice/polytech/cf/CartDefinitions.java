package fr.unice.polytech.cf;

import fr.unice.polytech.cf.components.CartHandler;
import fr.unice.polytech.cf.components.Catalog;
import fr.unice.polytech.cf.exceptions.EmptyCartException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartDefinitions {
    CartHandler cartHandler;
    Catalog catalog;
    boolean ordered;
    Order order;
    boolean isVIP;

    @Given("the cart contains {int} cookies {word}")
    public  void theCartContainsThisCookies(int number, String name){
        cartHandler = new CartHandler();
        catalog = new Catalog();
        try {
            cartHandler.addCookie(catalog.getCookie(name), number);}
        catch (RuntimeException ignored){}
        System.out.println(catalog.getCookie(name).getName() + catalog.getCookie(name).getCookingTime() + catalog.getCookie(name).getPrice());
    }

    @And("the cookie {word} price is {double}")
    public void theCookiePriceIs(String name, double price){
        assert catalog.getCookie(name).getPrice() == price;
    }

    @And("the cookie {word} cooking time is {int}")
    public void theCookieTimeIs(String name, int time){
        assert catalog.getCookie(name).getCookingTime() == time;
    }

    @And("the client is VIP")
    public void theClientIsVIP(){isVIP=true;}

    @And("the client isn't VIP")
    public void theClientIsntVIP(){isVIP=false;}

    @When("the client add {int} {word} to the cart")
    public void the_client_add_cookie_s_to_the_cart(Integer number, String name) {
        try {
            cartHandler.addCookie(catalog.getCookie(name), number);}
        catch (RuntimeException ignored){}
        System.out.println("add to cart" + cartHandler.getCookies().values());
    }

    @When("the client confirm the order")
    public void theClientConfirmTheOrder() throws CloneNotSupportedException {
        try {
            ordered = true;
            order= cartHandler.confirmOrder(isVIP);
        }catch (EmptyCartException e){
            ordered = false;
        }
    }

    @Then("the cart should contain {int} cookies")
    public void the_cart_should_contain_cookies(Integer number) {
        assert (cartHandler.getNbCookies()==number);
    }

    @Then("the cart should contain {int} cookies {word}")
    public void theCartShouldContainThisCookies(int number, String name){
        System.out.println("init cart" + cartHandler.getCookies().values());
        if(catalog.hasCookie(name)) assert (cartHandler.getCookies().get(catalog.getCookie(name)) == number);
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
    public void theCartIsEmpty(){assert (cartHandler.getNbCookies() == 0);}

    @Then("the cart's price should be {double}")
    public void thePriceShouldBe(double price){assert cartHandler.getPrice() == price;}

    @Then("the cart's cooking time should be {int}")
    public void theCookingTimeshouldBe(int time){
        System.out.println(cartHandler.getCookingTime());
        assert cartHandler.getCookingTime()==time;}

    @Then("the price should be {double}")
    public void thePriceShouldBe(int price){
        assert order.getPrice()==price;
    }
}
