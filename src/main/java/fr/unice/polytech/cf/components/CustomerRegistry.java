package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.interfaces.CustomerFinder;
import fr.unice.polytech.cf.interfaces.CustomerRegistration;
import org.springframework.stereotype.Component;

@Component
public class CustomerRegistry implements CustomerFinder, CustomerRegistration {
}
