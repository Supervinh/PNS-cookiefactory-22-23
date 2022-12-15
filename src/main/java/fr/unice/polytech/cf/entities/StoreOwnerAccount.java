package fr.unice.polytech.cf.entities;

import java.time.LocalTime;

public class StoreOwnerAccount {
    private final Store ownedStore;

    public StoreOwnerAccount() {
        ownedStore = new Store("Unnamed Store", LocalTime.of(8, 0), LocalTime.of(21, 0));
    }

}
