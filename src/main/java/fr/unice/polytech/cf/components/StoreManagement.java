package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.interfaces.explorer.StoreFinder;
import fr.unice.polytech.cf.interfaces.modifier.StoreModifier;
import fr.unice.polytech.cf.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Component
public class StoreManagement implements StoreModifier, StoreFinder {
    StoreRepository storeRepository;

    @Autowired
    public StoreManagement(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Store addStore(String name, LocalTime OpeningTime, LocalTime ClosingTime) {
        Store newStore = new Store(name, OpeningTime, ClosingTime);
        storeRepository.save(newStore, newStore.getId());
        return newStore;
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

    @Override
    public Optional<Store> findByName(String name) {
        return StreamSupport.stream(storeRepository.findAll().spliterator(), false)
                .filter(st -> name.equals(st.getName())).findAny();
    }
}
