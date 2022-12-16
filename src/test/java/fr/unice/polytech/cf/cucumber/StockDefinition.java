package fr.unice.polytech.cf.cucumber;

public class StockDefinition {
    /*CartHandler cartHandler;
    CatalogHandler catalog;
    StockHandler stock;
    BasicCookie basicCookie;

    @Given("I want to order {int} cookie")
    public void iWantToOrderCookie(int nbCookies) {
        cartHandler = new CartHandler();
        stock = new StockHandler();
        catalog = new CatalogHandler(stock);
        basicCookie = catalog.getCookies().get(0);
    }

    @When("the ingredients used for the cookies are available")
    public void theIngredientsUsedForTheCookiesAreAvailable() {
        if (stock.removeCookieFromStock(basicCookie))
            cartHandler.addCookie(basicCookie, 1);
    }


    @When("the ingredients used for the cookies aren't available")
    public void theIngredientsUsedForTheCookiesArenTAvailable() {
        stock.removeIngredientByName(basicCookie.getFlavour(), 4);
        try {
            stock.removeCookieFromStock(basicCookie);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Then("the cart should contain {int} cookie")
    public void the_cart_should_contain_cookies(Integer number) {
        assert (cartHandler.getNbCookies()==number);
    }*/
}
