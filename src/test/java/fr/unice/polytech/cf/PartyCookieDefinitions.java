package fr.unice.polytech.cf;

import fr.unice.polytech.cf.components.CartHandler;
import fr.unice.polytech.cf.components.Catalog;
import fr.unice.polytech.cf.entities.cookies.PartyCookie;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PartyCookieDefinitions {
    Catalog catalog=new Catalog();
    Store store;
    CartHandler cartHandler;
    boolean isAdded;

    @Given("the catalog does has the cookie {word}")
    public void doesHas(String cookie){assert catalog.hasCookie(cookie);}

    @Given("the store can make party cookies")
    public void canMake(){
        List<CookAccount> cook = new ArrayList<CookAccount>();
        cook.add(new CookAccount("name",LocalTime.of(10,0), LocalTime.of(19,30),true));
        store = new Store("Default Store", LocalTime.of(10,0), LocalTime.of(19,30));
        store.getStoreSchedule().addcook(cook);
        assert store.canMakePartyCookie();
    }

    @Given("the store can't make party cookies")
    public void cantMake(){
        catalog = new Catalog();
        List<CookAccount> cook = new ArrayList<CookAccount>();
        cook.add(new CookAccount("name",LocalTime.of(10,0), LocalTime.of(19,30), false));
        store = new Store("Default Store", LocalTime.of(10,0), LocalTime.of(19,30));
        store.getStoreSchedule().addcook(cook);
        assert !store.canMakePartyCookie();
    }

    @And("a {word} cookie's price is {double}")
    public void price(String cookie, double i){
        assert catalog.getCookie(cookie).getPrice()==i;
    }

    @And("the client decides to add the {word} cookie to the cart")
    public void addToCart(String cookie){
        cartHandler =new CartHandler(store);
        System.out.println(catalog.getCookie(cookie).getClass()==PartyCookie.class);
        try{
            cartHandler.addCookie(catalog.getCookie(cookie), 1);
            isAdded=true;
        }
        catch(RuntimeException e){isAdded=false;}
    }

    @Then("the {word} cookie's price should be {double}")
    public void priceIs(String cookie, double i){
        System.out.println(catalog.getCookie(cookie).getPrice());
        assert catalog.getCookie(cookie).getPrice()==i;
    }

    @Then("the cookie should be added")
    public void isAdded(){assert isAdded==true;}

    @Then(("the cookie shouldn't be added"))
    public void isNotAdded(){assert isAdded==false;}
}
