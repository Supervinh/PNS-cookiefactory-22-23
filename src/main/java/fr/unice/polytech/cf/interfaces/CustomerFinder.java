package fr.unice.polytech.cf.interfaces;

import fr.unice.polytech.cf.entities.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerFinder {

    Optional<Customer> findByName(String name);

    Optional<Customer> findById(UUID id);
}
