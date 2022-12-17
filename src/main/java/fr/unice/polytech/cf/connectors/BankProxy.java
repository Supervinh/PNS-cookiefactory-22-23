package fr.unice.polytech.cf.connectors;

import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.cf.interfaces.modifier.Bank;
import org.springframework.stereotype.Component;

@Component
public class BankProxy implements Bank {

    @Override
    public boolean pay(Customer customer, double value) {
        // should be an external connection to a bank service
        return (value > 0);
    }

}