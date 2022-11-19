package fr.unice.polytech.cf;

import fr.unice.polytech.cf.exceptions.EmptyCartException;
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

import java.time.LocalTime;
import java.util.*;

public class OrderDefinitions {
    Order currentOrder;
    Catalog catalog;
    boolean possible;
    CookAccount cook = new CookAccount("Gordon", LocalTime.of(8,0,0,0), LocalTime.of(17,0,0,0));
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
            cook = new CookAccount("Gordon", LocalTime.of(8,0,0,0), LocalTime.of(17,0,0,0));
        else{
            for(int i =0; i<nbOrders; i++){
                currentOrder = new Order(new Cart());
                currentOrder.setCommandState(CommandState.WORKING_ON_IT);
            }
            cook = new CookAccount("Gordon", LocalTime.of(8,0,0,0), LocalTime.of(17,0,0,0));
        }
    }

    @When("the cook receive an order")
    public void theCookReceiveAnOrder() {
        currentOrder = new Order(new Cart());
        currentOrder.setCommandState(CommandState.PAID);
        cook.addOrder(currentOrder);
        cook.prepareOrder(currentOrder);
    }

    @Then("the order's status should be {word}")
    public void theOrderSStatusShouldBe(String status) {
            assert (currentOrder.getCommandState()==CommandState.valueOf(status));
    }

    @And("the order is not paid")
    public void theOrderIsNotPaid() {
        currentOrder.setCommandState(CommandState.UNPAID);
      /*Order unpaidOrder = new Order(new Cart());
        unpaidOrder.setCommandState(CommandState.UNPAID);
        cook.addOrder(unpaidOrder);
        cook.prepareOrder(unpaidOrder);*/
    }
    @And("the order is paid")
    public void theLastOrderIsPaid(){
        Cart cart=new Cart();
        cart.addCookie(new Cookie("chocolate", Cooking.CRUNCHY,
                new Ingredient(IngredientEnum.DOUGH, "Chocolate", 3),
                new Ingredient(IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>()),1);
        Order paidOrder = new Order(cart);
        paidOrder.setCommandState(CommandState.PAID);
        try {
            client.addOrder(paidOrder);
        } catch (OrderCancelledTwiceException e) {
            e.printStackTrace();
        }
        client.getCurrentOrders().get(client.getCurrentOrders().size()-1).setCommandState(CommandState.PAID);
    }

    @When("the cook finishes to prepare the order")
    public void theCookFinishesToPrepareTheOrder() {
        cook.finishOrder(currentOrder);
    }

    @Given("the client has made an order")
    public void theClientHasMadeAnOrder() {
        try {
            client.addOrder(currentOrder = new Order(new Cart()));
        } catch (OrderCancelledTwiceException e) {
            e.printStackTrace();
        }
    }

    @And("the client's order is ready")
    public void theClientSOrderIsReady() {
        currentOrder.setCommandState(CommandState.READY);
    }

    @And("the client's order is not ready")
    public void theClientSOrderIsNotReady() {
        Random rnd = new Random();
        List<CommandState> possibleStates = new ArrayList<>( Arrays.asList(CommandState.values()));
        possibleStates.remove(CommandState.DELIVERED);
        possibleStates.remove(CommandState.READY);
        CommandState randomState = possibleStates.get(rnd.nextInt(possibleStates.size()-1));
        client.getCurrentOrders().get(0).setCommandState(randomState);
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
        assert (client.getCurrentOrders().get(0).getCommandState() != CommandState.DELIVERED);
    }
    @Then("the client receive a receipt for his last order")
    public void theClientReceiveAReceipt(){
        assert(client.getCurrentOrders().get(client.getCurrentOrders().size()-1).getreceipt().equals("5.5"+"\n"+"chocolate"+"\n"));
    }
}
