package fr.unice.polytech.cf.cucumber;

public class CartTooGoodToGoDefinitions {
    /*Store store = new Store("Default Store", LocalTime.of(10, 0), LocalTime.of(19, 30));
    CartHandlerTooGoodToGo cartTooGoodToGo;
    Order order;
    //Cook cook = new Cook("Gordon", LocalTime.of(8, 0, 0, 0), LocalTime.of(17, 0, 0, 0));
    ArrayList<Order> listOrders;

    @Given("the client made an order")
    public void orderIsMade() {
        CartHandler cartHandler = new CartHandler();
        cartHandler.addCookie(new BasicCookie("chocolate", Cooking.CRUNCHY,
                new Ingredient(storeId, IngredientEnum.DOUGH, "Chocolate", 3),
                new Ingredient(storeId, IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>()), 1);
        order = new Order(cartHandler);
    }

    @And("the order are too old")
    public void theOrderAreTooOld() {
        order.setOrderState(OrderState.TOO_GOOD_TO_GO);
    }

    @And("the order cost is {float}")
    public void theOrderCostX(float price) {
        assert (order.getPrice() == price);
    }

    @Then("the CartTGTG cost {float}")
    public void theCartTGTGCostX(float price) {
        listOrders = new ArrayList<Order>();
        listOrders.add(order);
        cartTooGoodToGo = new CartHandlerTooGoodToGo(store, listOrders);

        assert ((float) cartTooGoodToGo.getPrice() == price);
    }
*/
}
