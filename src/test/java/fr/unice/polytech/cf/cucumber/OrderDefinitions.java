package fr.unice.polytech.cf.cucumber;

public class OrderDefinitions {
//    Order currentOrder;
//    CatalogHandler catalog;
//    boolean possible;
//    //Cook cook = new Cook("Gordon", LocalTime.of(8,0,0,0), LocalTime.of(17,0,0,0));
//    Customer client = new Customer("Tom", "Bevan", "tom.bevan@etu.unice.fr");
//
//    @And("the catalog does not contains the cookie {word}")
//    public void the_catalog_does_not_contains_cookie(String cookie) {
//        catalog = new CatalogHandler();
//        possible = false;
//        assert (!catalog.hasCookie(cookie));
//
//    }
//
//    @Given("the cart doesn't contain the cookie {string}")
//    public void the_catalog_doesn_t_contain_cookie(String cookie) {
//        if (!catalog.hasCookie(cookie)) {
//            System.out.println("the catalog doesn't contain the cookie");
//        }
//    }
//
//    @Given("the cook is working and has {int} order")
//    public void theCookIsWorkingAndHasOrder(int nbOrders) {
//        if (nbOrders == 0)
//            cook = new Cook("Gordon", LocalTime.of(8, 0, 0, 0), LocalTime.of(17, 0, 0, 0));
//        else {
//            for (int i = 0; i < nbOrders; i++) {
//                currentOrder = new Order(new CartHandler());
//                currentOrder.setOrderState(OrderState.WORKING_ON_IT);
//            }
//            cook = new Cook("Gordon", LocalTime.of(8, 0, 0, 0), LocalTime.of(17, 0, 0, 0));
//        }
//    }
//
//    @When("the cook receive an order")
//    public void theCookReceiveAnOrder() {
//        currentOrder = new Order(new CartHandler());
//        currentOrder.setOrderState(OrderState.PAID);
//        cook.addOrder(currentOrder);
//        cook.prepareOrder(currentOrder);
//    }
//
//    @Then("the order's status should be {word}")
//    public void theOrderSStatusShouldBe(String status) {
//        assert (currentOrder.getOrderState() == OrderState.valueOf(status));
//    }
//
//    @And("the order is not paid")
//    public void theOrderIsNotPaid() {
//        currentOrder.setOrderState(OrderState.UNPAID);
//      *//*Order unpaidOrder = new Order(new Cart());
//        unpaidOrder.setOrderState(OrderState.UNPAID);
//        cook.addOrder(unpaidOrder);
//        cook.prepareOrder(unpaidOrder);*//*
//    }
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
//    @When("the cook finishes to prepare the order")
//    public void theCookFinishesToPrepareTheOrder() {
//        cook.finishOrder(currentOrder);
//    }
//
//    @Given("the client has made an order")
//    public void theClientHasMadeAnOrder() {
//        try {
//            client.addOrder(currentOrder = new Order(new CartHandler()));
//        } catch (OrderCancelledTwiceException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @And("the client's order is ready")
//    public void theClientSOrderIsReady() {
//        currentOrder.setOrderState(OrderState.READY);
//    }
//
//    @And("the client's order is not ready")
//    public void theClientSOrderIsNotReady() {
//        Random rnd = new Random();
//        List<OrderState> possibleStates = new ArrayList<>(Arrays.asList(OrderState.values()));
//        possibleStates.remove(OrderState.DELIVERED);
//        possibleStates.remove(OrderState.READY);
//        OrderState randomState = possibleStates.get(rnd.nextInt(possibleStates.size() - 1));
//        client.getCurrentOrders().get(0).setOrderState(randomState);
//    }
//
//    @When("the client comes to retrieve the order")
//    public void theClientComesToRetrieveTheOrder() {
//        try {
//            client.retrieveOrder();
//        } catch (OrderNotReadyException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Then("the order's status should be the same as before")
//    public void theOrderSStatusShouldBeTheSameAsBefore() {
//        assert (client.getCurrentOrders().get(0).getOrderState() != OrderState.DELIVERED);
//    }
//
//    @Then("the client receive a receipt for his last order")
//    public void theClientReceiveAReceipt() {
//        assert (client.getCurrentOrders().get(client.getCurrentOrders().size() - 1).getreceipt().equals("5.5" + "\n" + "chocolate" + "\n"));
//    }
//
//
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
