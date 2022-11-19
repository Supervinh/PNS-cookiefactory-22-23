package fr.unice.polytech.cf;

import fr.unice.polytech.cf.cookies.Cookie;
import fr.unice.polytech.cf.exceptions.OrderCancelledTwiceException;
import fr.unice.polytech.cf.exceptions.OrderNotReadyException;
import fr.unice.polytech.cf.ingredients.Cooking;
import fr.unice.polytech.cf.ingredients.Ingredient;
import fr.unice.polytech.cf.ingredients.IngredientEnum;
import fr.unice.polytech.cf.ingredients.Mix;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.*;

public class OrderDefinitions {
    Catalog catalog;
    boolean possible;
    CookAccount cook = new CookAccount("Gordon", false);
    UserAccount client = new UserAccount();

    @And("the catalog does not contains the cookie {word}")
    public void the_catalog_does_not_contains_cookie(String cookie) {
        catalog = new Catalog();
        possible = false;
        assert(!catalog.hasCookie(cookie));

    }

    @Given("the cart doesn't contain the cookie {string}")
    public void the_catalog_doesn_t_contain_cookie(String cookie) {
        if (!catalog.hasCookie(cookie)) {
            System.out.println("the catalog doesn't contain the cookie");
        }
    }

    @Given("the cook is working and has {int} order")
    public void theCookIsWorkingAndHasOrder(int nbOrders) {
        if (nbOrders == 0)
            cook = new CookAccount("Gordon", false);
        else{
            Order cookOrder = new Order(new Cart());
            cookOrder.setOrderState(OrderState.WORKING_ON_IT);
            cook = new CookAccount("Gordon", cookOrder);
        }
    }

    @When("the cook receive an order")
    public void theCookReceiveAnOrder() {
        Order receivedOrder = new Order(new Cart());
        receivedOrder.setOrderState(OrderState.PAID);
        cook.setOrder(receivedOrder);
        cook.prepareOrder();
    }

    @Then("the order's status should be {word}")
    public void theOrderSStatusShouldBe(String status) {
        if (cook.getOrder() != null)
            assert (cook.getOrder().getOrderState()== OrderState.valueOf(status));
        else
            assert (client.getOrderHistory().getOrder(0).getOrderState()== OrderState.valueOf(status));
    }

    @And("the order is not paid")
    public void theOrderIsNotPaid() {
        Order unpaidOrder = new Order(new Cart());
        unpaidOrder.setOrderState(OrderState.UNPAID);
        cook.setOrder(unpaidOrder);
        cook.prepareOrder();
    }
    @And("the order is paid")
    public void theLastOrderIsPaid(){
        Cart cart=new Cart();
        cart.addCookie(new Cookie("chocolate", Cooking.CRUNCHY,
                new Ingredient(IngredientEnum.DOUGH, "Chocolate", 3),
                new Ingredient(IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>()),1);
        Order paidOrder = new Order(cart);
        paidOrder.setOrderState(OrderState.PAID);
        try {
            client.addOrder(paidOrder);
        } catch (OrderCancelledTwiceException e) {
            e.printStackTrace();
        }
        client.getCurrentOrders().get(client.getCurrentOrders().size()-1).setOrderState(OrderState.PAID);
    }

    @When("the cook finishes to prepare the order")
    public void theCookFinishesToPrepareTheOrder() {
        cook.finishOrder();
    }

    @Given("the client has made an order")
    public void theClientHasMadeAnOrder() {
        try {
            client.addOrder(new Order(new Cart()));
        } catch (OrderCancelledTwiceException e) {
            e.printStackTrace();
        }
    }

    @And("the client's order is ready")
    public void theClientSOrderIsReady() {
        client.getCurrentOrders().get(0).setOrderState(OrderState.READY);
    }

    @And("the client's order is not ready")
    public void theClientSOrderIsNotReady() {
        Random rnd = new Random();
        List<OrderState> possibleStates = new ArrayList<>( Arrays.asList(OrderState.values()));
        possibleStates.remove(OrderState.DELIVERED);
        possibleStates.remove(OrderState.READY);
        OrderState randomState = possibleStates.get(rnd.nextInt(possibleStates.size()-1));
        client.getCurrentOrders().get(0).setOrderState(randomState);
    }

    @When("the client comes to retrieve the order")
    public void theClientComesToRetrieveTheOrder() {
        try {
            client.retrieveOrder();
        }catch (OrderNotReadyException e){
            System.out.println(e.getMessage());
        }
    }

    @Then("the order's status should be the same as before")
    public void theOrderSStatusShouldBeTheSameAsBefore() {
        assert (client.getCurrentOrders().get(0).getOrderState() != OrderState.DELIVERED);
    }
    @Then("the client receive a receipt for his last order")
    public void theClientReceiveAReceipt(){
        assert(client.getCurrentOrders().get(client.getCurrentOrders().size()-1).getreceipt().equals("5.5"+"\n"+"chocolate"+"\n"));
    }
}
