package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.entities.cookies.CookieRecipe;
import fr.unice.polytech.cf.interfaces.CatalogExplorer;
import fr.unice.polytech.cf.interfaces.CatalogModifier;
import fr.unice.polytech.cf.interfaces.StockExplorer;
import fr.unice.polytech.cf.repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Component
public class CatalogHandler implements CatalogExplorer, CatalogModifier {
    private StockExplorer stock;
    private CatalogRepository catalogRepository;

    @Autowired
    public CatalogHandler(StockExplorer stock, CatalogRepository catalogRepository) {
        this.stock = stock;
        this.catalogRepository = catalogRepository;
    }

    @Override
    public void updateCatalog(Store store) {
        Iterable<CookieRecipe> cookies = catalogRepository.findAll();
        cookies.forEach(cookie -> {
            if (!stock.ingredientsCanBeRemovedFromStock(cookie.getIngredients(), store.getId())) {
                catalogRepository.deleteById(cookie.getId());
            }
        });
    }

    @Override
    public CookieRecipe getCookie(String name) {
        Optional<CookieRecipe> cookieRecipe = findByName(name);
        if (cookieRecipe.isPresent()) {
            return cookieRecipe.get();
        }
        throw new RuntimeException("Cookie recipe does not exist");
    }

    @Override
    public void addCookie(CookieRecipe cookieRecipe) {
        catalogRepository.save(cookieRecipe, cookieRecipe.getId());
    }

    @Override
    public void removeCookie(CookieRecipe cookieRecipe) {
        catalogRepository.deleteById(cookieRecipe.getId());
    }

    @Override
    public List<CookieRecipe> getCookies() {
        Iterable<CookieRecipe> cookies = catalogRepository.findAll();
        if (cookies == null) {
            return new ArrayList<>();
        }
        return StreamSupport.stream(cookies.spliterator(), false).toList();
    }

    @Override
    public boolean hasCookie(String name) {
        return findByName(name).isPresent();
    }

    @Override
    public Optional<CookieRecipe> findByName(String name) {
        return StreamSupport.stream(catalogRepository.findAll().spliterator(), false)
                .filter(i -> name.equals(i.getName())).findAny();
    }

}
