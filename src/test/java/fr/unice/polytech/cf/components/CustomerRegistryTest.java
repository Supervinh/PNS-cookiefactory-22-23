package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.cf.exceptions.AlreadyExistingCustomerException;
import fr.unice.polytech.cf.interfaces.explorer.CustomerFinder;
import fr.unice.polytech.cf.interfaces.modifier.CustomerRegistration;
import fr.unice.polytech.cf.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerRegistryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerRegistration registry;

    @Autowired
    private CustomerFinder finder;

    private final String name = "John";
    private final String surname = "wick";
    private final String mail = "johnwick@gmail.com";

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    public void unknownCustomer() {
        assertFalse(finder.findByName(name).isPresent());
    }

    @Test
    public void registerCustomer() throws Exception {
        Customer returned = registry.register(name, surname, mail);
        Optional<Customer> customer = finder.findByName(name);
        assertTrue(customer.isPresent());
        Customer john = customer.get();
        assertEquals(john, returned);
        assertEquals(john, finder.findById(returned.getId()).get());
        assertEquals(name, john.getName());
        assertEquals(surname, john.getSurname());
    }

    @Test
    public void cannotRegisterTwice() throws Exception {
        registry.register(name, surname, mail);
        Assertions.assertThrows(AlreadyExistingCustomerException.class, () -> {
            registry.register(name, surname, mail);
        });
    }
}
