package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.*;
import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.entities.ingredients.Cooking;
import fr.unice.polytech.cf.entities.ingredients.Ingredient;
import fr.unice.polytech.cf.entities.ingredients.IngredientEnum;
import fr.unice.polytech.cf.entities.ingredients.Mix;
import fr.unice.polytech.cf.interfaces.modifier.CustomerRegistration;
import fr.unice.polytech.cf.interfaces.modifier.OrderProcessing;
import fr.unice.polytech.cf.repositories.CustomerRepository;
import fr.unice.polytech.cf.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class KitchenTest {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private CustomerRegistration registry;

    @Autowired
    private OrderProcessing processor;



    private Set<Item> items;
    private Customer john;
    LocalTime time= LocalTime.of(8,30);
    LocalTime time2= LocalTime.of(20,30);
    private Store store;
    LocalDateTime date=LocalDateTime.of(2022,12,17,22,0);
    @BeforeEach
    public void setUpContext() throws Exception {
        customerRepository.deleteAll();
        orderRepository.deleteAll();
        store=new Store("store1",0.1,time,time2);
        items = new HashSet<>();
        items.add(new Item(new BasicCookie("chocolate", Cooking.CRUNCHY,
                new Ingredient(store.getId(), IngredientEnum.DOUGH, "Chocolate", 3),
                new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>()), 3));
        items.add(new Item(new BasicCookie("DARK_TEMPTATION", Cooking.CRUNCHY,
                new Ingredient(store.getId(), IngredientEnum.DOUGH, "Chocolate", 3),
                new Ingredient(store.getId(), IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, new ArrayList<>()), 1));
        john = registry.register("john", "wick","johnwick@gmail.com");
    }

    @Test
    void processCommand() throws Exception {
        Order inProgress = new Order(john, items,store.getId(),date);
        inProgress.setOrderState(OrderState.PAID);
        processor.prepareOrder(inProgress);
        assertEquals(inProgress.getOrderState(), OrderState.WORKING_ON_IT);
        processor.finishOrder(inProgress);
        assertEquals(OrderState.READY,inProgress.getOrderState() );
    }
}
