package fr.unice.polytech.cf.interfaces;

import fr.unice.polytech.cf.entities.Store;

import java.time.LocalTime;

public interface StoreModifier {
    void addStore(String name, LocalTime OpeningTime, LocalTime ClosingTime);

    void changeStoreOpeningTime(Store store, LocalTime newOpeningTime);

    void changeStoreClosingTime(Store store, LocalTime newClosingTime);

    void changeStoreTaxes(Store store, double storeTaxes);
}
