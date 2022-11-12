package fr.unice.polytech.cf;

import fr.unice.polytech.cf.exceptions.OrderNotReadyException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserAccountDefinitions {
    UserAccount userAccount;

    @Given("the account has ordered {int} cookies")
    public void theAccountHasCookies(int i){
        userAccount = new UserAccount();
        userAccount.setCookiesForVIP(i);
    }

    @Given("he has made an order")
    public void heHasMadeAnOrder() {
        userAccount = new UserAccount();
        userAccount.addOrder(new Order(new Cart()));
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
        userAccount.getCurrentOrders().get(0).setCommandState(CommandState.READY);
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
}
