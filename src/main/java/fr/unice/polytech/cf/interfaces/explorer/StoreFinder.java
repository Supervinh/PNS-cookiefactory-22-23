package fr.unice.polytech.cf.interfaces.explorer;

import fr.unice.polytech.cf.entities.Store;

import java.util.Optional;

public interface StoreFinder {

    Optional<Store> findByName(String name);
}
