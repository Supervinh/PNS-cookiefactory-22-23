package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.cf.exceptions.AlreadyExistingCustomerException;
import fr.unice.polytech.cf.interfaces.CustomerFinder;
import fr.unice.polytech.cf.interfaces.CustomerRegistration;
import fr.unice.polytech.cf.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Component
public class CustomerRegistry implements CustomerFinder, CustomerRegistration {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerRegistry(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer register(String name, String surname, String email) throws AlreadyExistingCustomerException {
        if(findByName(name).isPresent())
            throw new AlreadyExistingCustomerException(name);
        Customer newcustomer = new Customer(name, surname, email);
        customerRepository.save(newcustomer,newcustomer.getId());
        return newcustomer;
    }

    @Override
    public Optional<Customer> findByName(String name) {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .filter(cust -> name.equals(cust.getName())).findAny();
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return customerRepository.findById(id);
    }
}
