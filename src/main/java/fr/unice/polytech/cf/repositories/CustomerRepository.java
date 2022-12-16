package fr.unice.polytech.cf.repositories;

import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.repositories.BasicRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CustomerRepository extends BasicRepositoryImpl<Customer, UUID> {
}
