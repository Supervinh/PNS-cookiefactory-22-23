package fr.unice.polytech.cf.interfaces;

import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.cf.exceptions.AlreadyExistingCustomerException;

public interface CustomerRegistration {
    Customer register(String name, String surname, String email)
            throws AlreadyExistingCustomerException;
}
