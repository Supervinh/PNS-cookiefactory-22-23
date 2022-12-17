package fr.unice.polytech.cf.interfaces.modifier;

import fr.unice.polytech.cf.entities.Cook;
import fr.unice.polytech.cf.exceptions.AlreadyExistingCustomerException;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface CookRegistration {

    Cook addCook(String name, LocalTime startWork, LocalTime endWork, UUID storeId) throws AlreadyExistingCustomerException;

    List<Cook> addCooks(List<Cook> cooks);

    void removeCook(Cook cook);
}
