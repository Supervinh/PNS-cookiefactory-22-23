package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.cf.entities.Item;
import fr.unice.polytech.cf.entities.Order;
import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.exceptions.EmptyCartException;
import fr.unice.polytech.cf.interfaces.CartModifier;
import fr.unice.polytech.cf.interfaces.CartProcessor;
import fr.unice.polytech.cf.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class CartHandler implements CartModifier, CartProcessor {
    private CustomerRepository customerRepository;
    private StockHandler stock;
    private Store store;


    @Autowired
    public CartHandler(Store store, CustomerRepository customerRegistry, StockHandler stock) {
        this.store = store;
        this.customerRepository = customerRegistry;
        this.stock = stock;
    }

    @Override
    public void addCookie(Customer customer, Store store, Item item) {  //TODO check the store's opening time

        // if(cookie.getClass()==PartyCookie.class && !store.canMakePartyCookie()) throw new RuntimeException("This store can't make party cookies");
        int newQuantity = item.getQuantity();
        Set<Item> items = customer.getCart();
        Optional<Item> existing = items.stream().filter(e -> e.getCookie().equals(item.getCookie())).findFirst();
        if (isEnoughIngredientsInStock(item, store, customer)) {
            if (existing.isPresent()) {
                newQuantity += existing.get().getQuantity();
            }
            if (newQuantity < 0) {
                throw new RuntimeException("Not a positive number of cookies");
            } else {
                existing.ifPresent(items::remove);
                if (newQuantity > 0) {
                    items.add(new Item(item.getCookie(), newQuantity));
                }
            }
            customer.setCart(items);

        }


    }

    @Override
    public boolean isEnoughIngredientsInStock(Item item, Store store, Customer customer) {
        Set<Item> items = customer.getCart();
        items.add(item);
        List<Ingredient> ingredientsToCheck = new ArrayList<>();
        for (Item i : items) {
            ingredientsToCheck.addAll(i.getCookie().getIngredients());
        }
        return stock.ingredientsCanBeRemovedFromStock(ingredientsToCheck, store.getId());
    }


    @Override
    public Map<BasicCookie, Integer> getCookies() {
        return null;
    }

    @Override
    public double getPrice(Customer customer) {
        double price = customer.getCart().stream().mapToDouble(item -> item.getQuantity() * item.getCookie().getPrice()).sum();
        if (customer.isVIP()) {
            price *= 0.9;
        }
        return price;
    }

    @Override
    public double getCookingTime(Customer customer) {
        return customer.getCart().stream().mapToDouble(item -> item.getQuantity() * item.getCookie().getCookingTime()).sum();
    }

    @Override
    public int getNbCookies(Customer customer) {
        return customer.getCart().stream().mapToInt(Item::getQuantity).sum();
    }

    @Override
    public List<Ingredient> getIngredientsFromCart(Customer customer) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (Item item : customer.getCart()) {
            ingredients.addAll(item.getCookie().getIngredients());
        }
        return ingredients;
    }

    @Override
    public Order confirmOrder(Customer customer) throws EmptyCartException, CloneNotSupportedException {
        if (!customer.getCart().isEmpty()) {
            Order order = new Order((CartHandler) this.clone());
            stock.removeIngredientsFromStock(getIngredientsFromCart(customer), store.getId());
            customer.setCart(new HashSet<>());
            customerRepository.save(customer, customer.getId());
            return order;
        } else {
            throw new EmptyCartException();
        }
    }
}
