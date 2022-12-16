package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.*;
import fr.unice.polytech.cf.entities.OrderState;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.exceptions.EmptyCartException;
import fr.unice.polytech.cf.exceptions.OrderCancelledTwiceException;
import fr.unice.polytech.cf.exceptions.PaymentException;
import fr.unice.polytech.cf.interfaces.*;
import fr.unice.polytech.cf.repositories.CustomerRepository;
import fr.unice.polytech.cf.repositories.OrderRepository;
import fr.unice.polytech.cf.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Component
public class CartHandler implements CartModifier, CartProcessor, TooGoodToGoProcessing, OrderFinder {
    private CustomerRepository customerRepository;
    private StoreRepository storeRepository;

    private OrderRepository orderRepository;
    private Payment payment;
    private StockHandler stock;


    @Autowired
    public CartHandler(CustomerRepository customerRegistry, Payment payment, StoreRepository storeRepository, OrderRepository orderRepository, StockHandler stock) {
        this.customerRepository = customerRegistry;
        this.storeRepository = storeRepository;
        this.orderRepository = orderRepository;
        this.payment = payment;
        this.stock = stock;
        this.updateTooGoodToGo();
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
    public double getPrice(Customer customer, Store store) {
        double price = customer.getCart().stream().mapToDouble(item -> item.getQuantity() * item.getCookie().getPrice()).sum() * (1 + store.getTaxes());
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
    public Order confirmOrder(Customer customer, Store store, LocalDateTime retrieve) throws EmptyCartException, PaymentException, OrderCancelledTwiceException {
        if (!customer.getCart().isEmpty()) {
            if(retrieve == null){
                retrieve = LocalDateTime.now().plusHours(1);
            }
            Order order = payment.payOrder(customer, customer.getCart(), store, retrieve);
            stock.removeIngredientsFromStock(getIngredientsFromCart(customer), store.getId());
            customer.setCart(new HashSet<>());
            customerRepository.save(customer, customer.getId());
            return order;
        } else {
            throw new EmptyCartException();
        }
    }

    @Override
    public void updateTooGoodToGo(){
        List<Order> ordersToGoodToGo = findOrdersByState(OrderState.TOO_GOOD_TO_GO);
        for(Order order : ordersToGoodToGo){
            Optional<Store> optionalStore = storeRepository.findById(order.getStoreId());
            if (optionalStore.isPresent()){
                Store store = optionalStore.get();
                List<Item> cartTooGoodToGo = store.getCartTooGoodToGo();
                cartTooGoodToGo.addAll(order.getItems());
                store.setCartTooGoodToGo(applyTooGoodToGoPolicy(cartTooGoodToGo));
                storeRepository.save(store, store.getId());
            }
        }
        Iterable<Store> stores = storeRepository.findAll();
        for (Store store : stores){
            store.timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if(store.isOpen())
                        updateTooGoodToGo();
                    else{
                        store.timer.cancel();
                        store.timer.purge();
                        try {
                            store.timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    updateTooGoodToGo();
                                }
                            }, new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().plusDays(1).atTime(store.getOpeningTime()).toString()));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        };
                    }

                }
            }, 1000*60*180);
        }

    }

    @Override
    public List<Item> applyTooGoodToGoPolicy(List<Item> cartTooGoodToGo){
        for (Item item : cartTooGoodToGo){
            item.getCookie().setPrice(item.getCookie().getPrice()*0.3);
            item.getCookie().setCookingTime(0);
        }
        System.out.println("Sending cart too good to go");
        System.out.println(cartTooGoodToGo);
        return cartTooGoodToGo;
    }

    @Override
    public List<Order> findOrdersByState(Enum<OrderState> state){
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .filter(order -> order.getOrderState().equals(state))
                .collect(Collectors.toList());
    }
}
