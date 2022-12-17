package fr.unice.polytech.cf.repositories;

import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.cf.entities.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    Order order;

    @BeforeEach
    void setup() {
        orderRepository.deleteAll();
        Customer customer = new Customer("John", "Doe", "john@doe.com");
        order = new Order(customer, customer.getCart(), UUID.randomUUID(), null);
    }

    @Test
    void testSaveAndFind() {
        UUID genId = order.getId();
        assertNotNull(genId);
        orderRepository.save(order, order.getId());
        Optional<Order> foundJohnOpt = orderRepository.findById(genId);
        assertTrue(foundJohnOpt.isPresent());
        assertEquals(order, foundJohnOpt.get());
    }

    @Test
    void testDeleteAll() {
        assertEquals(0, orderRepository.count());
        orderRepository.save(order, order.getId());
        assertEquals(1, orderRepository.count());
        orderRepository.deleteAll();
        assertEquals(0, orderRepository.count());
    }


}