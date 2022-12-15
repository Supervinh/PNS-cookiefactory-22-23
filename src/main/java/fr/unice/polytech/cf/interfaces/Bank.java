package fr.unice.polytech.cf.interfaces;


import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.cf.exceptions.PaymentException;

public interface Bank {

    boolean pay(Customer customer, double value) throws PaymentException;
}
