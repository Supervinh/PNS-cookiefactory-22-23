package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.Item;
import fr.unice.polytech.cf.entities.Order;
import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.entities.cookies.PartyCookie;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.exceptions.EmptyCartException;
import fr.unice.polytech.cf.interfaces.CartModifier;
import fr.unice.polytech.cf.interfaces.CartProcessor;
import fr.unice.polytech.cf.interfaces.Cookie;
import fr.unice.polytech.cf.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class CartHandler implements CartModifier, CartProcessor {

    CustomerRegistry customerRegistry;

    IngredientRepository ingredientRepository;
    double price;
    protected int cookingTime;

    private Store store;

 /*   public CartHandler() {
        this.cookies = new HashMap<>();
        this.price = 0;
        this.cookingTime = 15;
        this.store = new Store("Default Store", LocalTime.of(10,0), LocalTime.of(19,30));
    }*/

    @Autowired
    public CartHandler(Store store, CustomerRegistry customerRegistry, IngredientRepository ingredientRepository) {
        this.price = 0;
        this.cookingTime = 15;
        this.store = store;
    }

    @Override
    public void addCookie(Customer customer, Item item) {  //TODO check the store's opening time

        // if(cookie.getClass()==PartyCookie.class && !store.canMakePartyCookie()) throw new RuntimeException("This store can't make party cookies");
        int newQuantity = item.getQuantity();
        Set<Item> items = customer.getCart();
        Optional<Item> existing = items.stream().filter(e -> e.getCookie().equals(item.getCookie())).findFirst();
        if (isEnoughIngredientsInStock(item, customer)) {
            if (existing.isPresent()) {
                newQuantity += existing.get().getQuantity();
            }
            if (newQuantity < 0){
                throw new RuntimeException("Not a positive number of cookies");
            } else {
                existing.ifPresent(items::remove);
                if (newQuantity > 0) {
                    items.add(new Item(item.getCookie(), newQuantity));
                }
            }
            customer.setCart(items);
            //price += Math.floor(basicCookie.getPrice() * (1 + store.getTaxes()) * 100 * number)/100.0;
            //cookingTime += basicCookie.getCookingTime() * number;

        }


    }

    // TODO check stock repository
    private boolean isEnoughIngredientsInStock(Item item, Customer customer) {
        Set<Item> items = customer.getCart();
        items.add(item);
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        for (Item i : items) {
            for (Ingredient ingredient : i.getCookie().getIngredients()) {
                for () {
                    return false;
                }
            }
        }
        for (BasicCookie aBasicCookie :){
            for (int i = 0; i< cookies.get(aBasicCookie); i++)
                cookiesToCheck.add(aBasicCookie);
        }
        return store.checkStock(cookiesToCheck);
    }

/*    @Override
    public Set<BasicCookie> getCookies() {
        return new HashMap<>(cookies);
    }*/

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getCookingTime() {
        return cookingTime;
    }

    @Override
    public int getNbCookies() {
        int sum = 0;
        for (Integer e : cookies.values()) {
            sum += e;
        }
        return sum;
    }

    @Override
    public Order confirmOrder(Customer customer) throws EmptyCartException, CloneNotSupportedException {
        if(customer.isVIP()) price = price - (price*0.1);
        if (!customer.getCart().isEmpty()) {
            Order order = new Order((CartHandler) this.clone());
            store.removeCookiesFromStock(this.cookies);
            customer.setCart(new HashSet<>());
            return order;
        } else {
            throw new EmptyCartException();
        }
    }

    @Override
    public Store getStore() {
        return store;
    }
}
