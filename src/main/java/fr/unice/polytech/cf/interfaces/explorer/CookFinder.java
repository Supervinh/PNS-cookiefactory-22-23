package fr.unice.polytech.cf.interfaces.explorer;

import fr.unice.polytech.cf.entities.Cook;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CookFinder {

    Iterable<Cook> getCooks();

    List<Cook> getCooksByStoreId(UUID storeId);

    Optional<Cook> findByName(String name);
}
