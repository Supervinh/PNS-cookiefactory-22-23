package fr.unice.polytech.cf.interfaces;

import fr.unice.polytech.cf.entities.Cook;

import java.util.List;

public interface CookRegistration {

    void addCook(Cook cook);
    void addCooks(List<Cook> cooks);

    void removeCook(Cook cook);
}
