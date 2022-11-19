package fr.unice.polytech.cf;

import fr.unice.polytech.cf.exceptions.OrderCancelledTwiceException;
import fr.unice.polytech.cf.exceptions.OrderNotPaidException;
import fr.unice.polytech.cf.exceptions.OrderNotReadyException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserAccountDefinitions {
    UserAccount userAccount;
    boolean receivedError;

    @Given("the account has ordered {int} cookies")
    public void theAccountHasCookies(int i){
        userAccount = new UserAccount();
        userAccount.setCookiesForVIP(i);
    }

    @Given("he has made an order")
    public void heHasMadeAnOrder() {
        userAccount = new UserAccount();
        try {
            userAccount.addOrder(new Order(new Cart()));
        } catch (OrderCancelledTwiceException e) {
            e.printStackTrace();
        }
    }

    @And("he isn't VIP")
    public void heIsntVIP(){assert userAccount.isVIP()==false;}

    @And("he is VIP")
    public void heIsVIP(){
        userAccount.setIsVIP(true);
        assert userAccount.isVIP()==true;
    }

    @And("his order is ready")
    public void hisOrderIsReady() {
        userAccount.getCurrentOrders().get(0).setOrderState(OrderState.READY);
    }

    @And("his current order should have {int} orders")
    public void hisCurrentOrder(int orders){assert userAccount.getCurrentOrders().size()==orders;}

    @When("he subscribes to the VIP")
    public void subscribeToVIP(){userAccount.subscribeVIP();}

    @When("he comes to retrieve the order")
    public void heComesToRetrieveTheOrder() {
        try {
            userAccount.retrieveOrder();
        }catch (OrderNotReadyException e){
            System.out.println(e.getMessage());
        }
    }

    @Then("he should be VIP")
    public void heShouldBeVIP(){assert userAccount.isVIP()==true;}

    @Then("he shouldn't be VIP")
    public void heShouldntBeVIP(){assert userAccount.isVIP()==false;}

    @Then("his order history should have {int} orders")
    public void hisHistoryHas(int orders){assert userAccount.getOrderHistory().getNbOrders()==orders;}

    @Given("his order is paid")
    public void his_order_is_paid() {
            userAccount.getCurrentOrders().get(0).setOrderState(OrderState.PAID);
    }
    @When("he cancels the order")
    public void he_cancels_the_order() {
        try {
            userAccount.cancelOrder();
        } catch (OrderNotPaidException e) {
            e.printStackTrace();
        }
    }
    @Then("the order should be removed")
    public void the_order_should_be_removed() {
        assert (userAccount.getCurrentOrders().isEmpty());
    }

    @Given("his order is working_on_it")
    public void his_order_is_working_on_it() {
        userAccount.getCurrentOrders().get(0).setOrderState(OrderState.WORKING_ON_IT);
    }

    @Then("the CommandState should be working_on_it")
    public void the_command_state_should_be_working_on_it() {
        assert (userAccount.getCurrentOrders().get(0).getOrderState()== OrderState.WORKING_ON_IT);
    }

    @And("the customer is forbidden to order")
    public void the_customer_is_forbidden_to_order() {
        try {
            userAccount.addOrder(new Order(new Cart()));
            userAccount.getCurrentOrders().get(0).setOrderState(OrderState.PAID);
            userAccount.getCurrentOrders().get(1).setOrderState(OrderState.PAID);
        } catch (OrderCancelledTwiceException e) {
            e.printStackTrace();
        }
        try {
            userAccount.cancelOrder();
            userAccount.cancelOrder();
        } catch (OrderNotPaidException e) {
            e.printStackTrace();
        }
    }
    @When("he orders something")
    public void he_orders_something() {
        try {
            userAccount.addOrder(new Order(new Cart()));
        } catch (OrderCancelledTwiceException e) {
            receivedError = true;
        }
    }
    @Then("he should receive an error")
    public void he_should_receive_an_error() {
        assert(receivedError);
    }
}
