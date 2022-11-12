package fr.unice.polytech.cf;

import java.time.LocalTime;

public class StoreOwnerAccount {
    private final Store ownedStore;

    public StoreOwnerAccount(){
        ownedStore = new Store("Unnamed Store", LocalTime.of(8,0), LocalTime.of(21,0));
    }

    public StoreOwnerAccount(String storeName){
        ownedStore = new Store(storeName, LocalTime.of(8,0), LocalTime.of(21,0));
    }

    public void changeStoreOpeningTime(LocalTime newOpeningTime){
        ownedStore.setOpeningTime(newOpeningTime);
    }

    public void changeStoreClosingTime(LocalTime newClosingTime){
        ownedStore.setClosingTime(newClosingTime);
    }

    public Store getOwnedStore(){
        return ownedStore;
    }
}
