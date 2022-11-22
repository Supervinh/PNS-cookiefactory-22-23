package fr.unice.polytech.cf;

import fr.unice.polytech.cf.components.CartHandler;
import fr.unice.polytech.cf.components.CartHandlerTooGoodToGo;
import fr.unice.polytech.cf.entities.cookies.Cookie;
import fr.unice.polytech.cf.ingredients.Cooking;
import fr.unice.polytech.cf.ingredients.Ingredient;
import fr.unice.polytech.cf.ingredients.IngredientEnum;
import fr.unice.polytech.cf.ingredients.Mix;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.time.LocalTime;
import java.util.ArrayList;

public class CartTooGoodToGoDefinitions {
    Store store=new Store("Default Store", LocalTime.of(10,0), LocalTime.of(19,30));
    CartHandlerTooGoodToGo cartTooGoodToGo;
    Order order;
    CookAccount cookAccount=new CookAccount("Gordon", LocalTime.of(8,0,0,0), LocalTime.of(17,0,0,0));;
    ArrayList<Order> listorder;
    @Given("the client made an order")
    public void orderIsMade(){
        CartHandler cartHandler =new CartHandler();
        cartHandler.addCookie(new Cookie("chocolate", Cooking.CRUNCHY,
                new Ingredient(IngredientEnum.DOUGH, "Chocolate", 3),
                new Ingredient(IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>()),1);
        order = new Order(cartHandler);
    }
    @And("the order are too old")
    public void theOrderAreTooOld(){
        order.setOrderState(OrderState.TOO_GOOD_TO_GO);
    }
    @And("the order cost is {float}")
    public void theOrderCostX(float price){
        assert(order.getPrice()==price);
    }
    @Then("the CartTGTG cost {float}")
    public void theCartTGTGCostX(float price){
        listorder=new ArrayList<Order>();
        listorder.add(order);
        cartTooGoodToGo=new CartHandlerTooGoodToGo(store,listorder);

        assert( (float)cartTooGoodToGo.getPrice()==price);
    }

}
