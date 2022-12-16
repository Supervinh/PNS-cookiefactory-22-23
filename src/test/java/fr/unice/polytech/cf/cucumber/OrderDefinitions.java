package fr.unice.polytech.cf.cucumber;

import fr.unice.polytech.cf.components.CatalogHandler;
import fr.unice.polytech.cf.entities.OrderState;
import fr.unice.polytech.cf.entities.*;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.entities.ingredients.Cooking;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.entities.ingredients.IngredientEnum;
import fr.unice.polytech.cf.entities.ingredients.Mix;
import fr.unice.polytech.cf.exceptions.*;
import fr.unice.polytech.cf.interfaces.*;
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
    CatalogHandler catalog;
    boolean possible;
    //Cook cook = new Cook("Gordon", LocalTime.of(8,0,0,0), LocalTime.of(17,0,0,0));
    //Customer client = new Customer("Tom", "Bevan", "tom.bevan@etu.unice.fr");

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CartModifier cartModifier;

    @Autowired
    private CustomerRegistration customerRegistration;

    @Autowired
    private CatalogExplorer catalogExplorer;

    @Autowired
    private CatalogModifier catalogModifier;

    @Autowired
    private StoreModifier storeModifier;

    @Autowired
    private CartProcessor cartProcessor;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private StockExplorer stockExplorer;

    @Autowired
    private StockModifier stockModifier;

    @Autowired
    private CatalogRepository catalogRepository;
    @Autowired
    private OrderProcessing orderProcessing;
    @Autowired
    private CookRepository cookRepository;
    @Autowired
    private ScheduleManagement scheduleManagement;
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
//
//    @Given("the cart doesn't contain the cookie {string}")
//    public void the_catalog_doesn_t_contain_cookie(String cookie) {
//        if (!catalog.hasCookie(cookie)) {
//            System.out.println("the catalog doesn't contain the cookie");
//        }
//    }

    @Given("the cook is working and has {int} order")
    public void theCookIsWorkingAndHasOrder(int nbOrders) throws AlreadyExistingCustomerException {
        cookRegistration.addCook("Gordon", LocalTime.of(8, 0, 0, 0), LocalTime.of(17, 0, 0, 0), store.getId());
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
        orderProcessing.assignOrder(currentOrder, store);
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

    //
//    @And("the order is paid")
//    public void theLastOrderIsPaid() {
//        CartHandler cartHandler = new CartHandler();
//        cartHandler.addCookie(new BasicCookie("chocolate", Cooking.CRUNCHY,
//                new Ingredient(storeId, IngredientEnum.DOUGH, "Chocolate", 3),
//                new Ingredient(storeId, IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
//                Mix.MIXED, new ArrayList<>()), 1);
//        Order paidOrder = new Order(cartHandler);
//        paidOrder.setOrderState(OrderState.PAID);
//        try {
//            client.addOrder(paidOrder);
//        } catch (OrderCancelledTwiceException e) {
//            e.printStackTrace();
//        }
//        client.getCurrentOrders().get(client.getCurrentOrders().size() - 1).setOrderState(OrderState.PAID);
//    }
//
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


//    @Then("the cart's cooking time should be {double}")
//    public void theCookingTimeshouldBe(double time) {
//        assert (customer.getCart().stream().mapToDouble(item -> item.getQuantity() * item.getCookie().getCookingTime()).sum() == time);
//    }
//
//    @And("the cookie {word} cooking time is {int}")
//    public void theCookieTimeIs(String name, int time) {
//        Item item = items.stream().filter(e -> e.getCookie().getName().equals(name)).findFirst().get();
//        item.getCookie().setCookingTime(time);
//    }
//
//
//    @Then("the price should be {double}")
//    public void thePriceShouldBe(int price) {
//        assert order.getPrice() == price;
//    }
//

}
