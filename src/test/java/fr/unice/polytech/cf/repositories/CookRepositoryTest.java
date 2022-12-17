package fr.unice.polytech.cf.repositories;

import fr.unice.polytech.cf.entities.Cook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CookRepositoryTest {

    @Autowired
    CookRepository cookRepository;

    Cook john;

    @BeforeEach
    void setup() {
        cookRepository.deleteAll();
        john = new Cook("john", LocalTime.of(8, 0), LocalTime.of(18, 0), UUID.randomUUID());
    }

    @Test
    void testSaveAndFind() {
        UUID genId = john.getId();
        assertNotNull(genId);
        cookRepository.save(john, john.getId());
        Optional<Cook> foundJohnOpt = cookRepository.findById(genId);
        assertTrue(foundJohnOpt.isPresent());
        assertEquals(john, foundJohnOpt.get());
    }

    @Test
    void testDeleteAll() {
        assertEquals(0, cookRepository.count());
        cookRepository.save(john, john.getId());
        assertEquals(1, cookRepository.count());
        cookRepository.deleteAll();
        assertEquals(0, cookRepository.count());
    }


}