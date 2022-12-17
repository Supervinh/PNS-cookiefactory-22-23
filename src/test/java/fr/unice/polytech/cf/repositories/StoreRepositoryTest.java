package fr.unice.polytech.cf.repositories;

import fr.unice.polytech.cf.entities.Customer;
import fr.unice.polytech.cf.entities.Order;
import fr.unice.polytech.cf.entities.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

    Store store;

    @BeforeEach
    void setup() {
        storeRepository.deleteAll();
        store = new Store("store", LocalTime.of(8, 0), LocalTime.of(20, 0));
    }

    @Test
    void testSaveAndFind() {
        UUID genId = store.getId();
        assertNotNull(genId);
        storeRepository.save(store, store.getId());
        Optional<Store> foundJohnOpt = storeRepository.findById(genId);
        assertTrue(foundJohnOpt.isPresent());
        assertEquals(store, foundJohnOpt.get());
    }

    @Test
    void testDeleteAll() {
        assertEquals(0, storeRepository.count());
        storeRepository.save(store, store.getId());
        assertEquals(1, storeRepository.count());
        storeRepository.deleteAll();
        assertEquals(0, storeRepository.count());
    }


}