package fr.unice.polytech.cf.repositories;

import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.repositories.BasicRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class StoreRepository extends BasicRepositoryImpl<Store, UUID> {
}
