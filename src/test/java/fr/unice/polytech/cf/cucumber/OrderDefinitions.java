package fr.unice.polytech.cf.cucumber;

import fr.unice.polytech.cf.entities.OrderState;
import fr.unice.polytech.cf.entities.*;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.entities.ingredients.Cooking;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.entities.ingredients.IngredientEnum;
import fr.unice.polytech.cf.entities.ingredients.Mix;
import fr.unice.polytech.cf.exceptions.*;
import fr.unice.polytech.cf.interfaces.explorer.StockExplorer;
import fr.unice.polytech.cf.interfaces.modifier.*;
import fr.unice.polytech.cf.repositories.*;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class OrderDefinitions {
    Order currentOrder;
    boolean possible;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CartModifier cartModifier;

    @Autowired
    private CustomerRegistration customerRegistration;

    @Autowired
    private StoreModifier storeModifier;

    @Autowired
    private CartProcessor cartProcessor;

    @Autowired
    private StockExplorer stockExplorer;

    @Autowired
    private StockModifier stockModifier;

    @Autowired
    private OrderProcessing orderProcessing;
    @Autowired
    private CookRepository cookRepository;
    @Autowired
    private CookRegistration cookRegistration;
    @Autowired
    private OrderModifier orderModifier;

    private Store store;
    private Customer customer;

    @Before
    public void settingUpContext() throws AlreadyExistingCustomerException {
        cookRepository.deleteAll();
        customerRepository.deleteAll();
        storeRepository.deleteAll();
        customer = customerRegistration.register("John", "Doe", "John@Doe.com");
        store = storeModifier.addStore("Store", LocalTime.of(8, 0, 0, 0), LocalTime.of(17, 0, 0, 0));

    }

    @And("the catalog does not contains the cookie {word}")
    public void the_catalog_does_not_contains_cookie(String cookie) {
        possible = false;

    }

    @Given("the cook is working and has {int} order")
    public void theCookIsWorkingAndHasOrder(int nbOrders) throws AlreadyExistingCustomerException {
        cookRegistration.addCook("Gordon", LocalTime.of(0, 0,0 , 0), LocalTime.of(23, 59, 59, 0), store.getId());
        if (nbOrders > 0) {
            for (int i = 0; i < nbOrders; i++) {
                currentOrder = new Order(customer, customer.getCart(), store.getId(), LocalDateTime.now().plusHours(1));
                currentOrder.setOrderState(OrderState.PAID);
                orderProcessing.assignOrder(currentOrder, store);
            }
        }
    }

    @When("the cook receive a paid order")
    public void theCookReceiveAnOrder() {
        currentOrder = new Order(customer, customer.getCart(), store.getId(), LocalDateTime.now().plusHours(1));
        currentOrder.setOrderState(OrderState.PAID);
        System.out.println(currentOrder.getOrderState());
        orderProcessing.assignOrder(currentOrder, store);
        System.out.println(currentOrder.getOrderState());
    }

    @Then("the order's status should be {word}")
    public void theOrderSStatusShouldBe(String status) {
        assert (currentOrder.getOrderState() == OrderState.valueOf(status));
    }

    @And("the cook receive an unpaid order")
    public void theOrderIsNotPaid() {
        currentOrder = new Order(customer, customer.getCart(), store.getId(), LocalDateTime.now().plusHours(1));
        orderProcessing.assignOrder(currentOrder, store);
    }

    @When("the cook finishes to prepare the order")
    public void theCookFinishesToPrepareTheOrder() {
        orderProcessing.finishOrder(currentOrder);
    }

    @Given("the client has made an order")
    public void theClientHasMadeAnOrder() throws EmptyCartException, PaymentException, OrderCancelledTwiceException, CloneNotSupportedException {
        stockModifier.addIngredient(new Ingredient(store.getId(), IngredientEnum.DOUGH, "dough", 2.5));
        stockModifier.addIngredient(new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "flavour", 3));
        cartModifier.addCookie(customer, store, new Item(new BasicCookie("chocolate", Cooking.CHEWY,
                stockExplorer.findIngredientByName("dough").get(0),
                stockExplorer.findIngredientByName("flavour").get(0),
                Mix.MIXED, new ArrayList<>()), 1));
        currentOrder = cartProcessor.confirmOrder(customer, store, null);
    }

    @And("the order is paid")
    public void orderIsPaid()  {
        currentOrder.setOrderState(OrderState.PAID);
    }

    @And("the client's order is ready")
    public void theClientSOrderIsReady() {
        currentOrder.setOrderState(OrderState.READY);
    }


    @And("the client's order is not ready")
    public void theClientSOrderIsNotReady() {
        Random rnd = new Random();
        List<OrderState> possibleStates = new ArrayList<>(Arrays.asList(OrderState.values()));
        possibleStates.remove(OrderState.DELIVERED);
        possibleStates.remove(OrderState.READY);
        OrderState randomState = possibleStates.get(rnd.nextInt(possibleStates.size() - 1));
        currentOrder.setOrderState(randomState);
    }

    @When("the client comes to retrieve the order")
    public void theClientComesToRetrieveTheOrder() {
        try {
            orderModifier.retrieveOrder(customer, currentOrder);
        } catch (OrderNotReadyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Then("the order's status shouldn't be delivered")
    public void theOrderSStatusShouldBeTheSameAsBefore() {
        assert (currentOrder.getOrderState() != OrderState.DELIVERED);
    }

    @Then("the client receive a receipt for his last order")
    public void theClientReceiveAReceipt() {
        assert (orderModifier.getReceipt(currentOrder).equals("5.5" + "\n" + "1 chocolate" + "\n"));
    }

}
