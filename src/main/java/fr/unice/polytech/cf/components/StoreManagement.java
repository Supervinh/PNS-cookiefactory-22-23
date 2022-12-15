package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.interfaces.StoreModifier;
import fr.unice.polytech.cf.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class StoreManagement implements StoreModifier {
    StoreRepository storeRepository;

    @Autowired
    public StoreManagement(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public void addStore(String name, LocalTime OpeningTime, LocalTime ClosingTime) {
        Store newStore = new Store(name, OpeningTime, ClosingTime);
        storeRepository.save(newStore, newStore.getId());
    }

    @Override
    public void changeStoreOpeningTime(Store store, LocalTime newOpeningTime){
        store.setOpeningTime(newOpeningTime);
    }

    @Override
    public void changeStoreClosingTime(Store store, LocalTime newClosingTime){
        store.setClosingTime(newClosingTime);
    }

    @Override
    public void changeStoreTaxes(Store store, double storeTaxes){
        store.setTaxes(storeTaxes);
    }
}
