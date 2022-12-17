package fr.unice.polytech.cf.repositories;

import fr.unice.polytech.cf.entities.cookies.Cookie;
import fr.unice.polytech.repositories.BasicRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CatalogRepository extends BasicRepositoryImpl<Cookie, UUID> {
}
