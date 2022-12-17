package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.entities.cookies.Cookie;
import fr.unice.polytech.cf.interfaces.explorer.CatalogExplorer;
import fr.unice.polytech.cf.interfaces.explorer.StockExplorer;
import fr.unice.polytech.cf.interfaces.modifier.CatalogModifier;
import fr.unice.polytech.cf.repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Component
public class CatalogHandler implements CatalogExplorer, CatalogModifier {
    private final StockExplorer stock;
    private final CatalogRepository catalogRepository;

    @Autowired
    public CatalogHandler(StockExplorer stock, CatalogRepository catalogRepository) {
        this.stock = stock;
        this.catalogRepository = catalogRepository;
    }

    @Override
    public void updateCatalog(Store store) {
        Iterable<Cookie> cookies = catalogRepository.findAll();
        cookies.forEach(cookie -> {
            if (!stock.ingredientsCanBeRemovedFromStock(cookie.getIngredients(), store.getId())) {
                catalogRepository.deleteById(cookie.getId());

            }
        });
    }

    @Override
    public Cookie getCookie(String name) {
        List<Cookie> cookies = findByName(name);
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        throw new RuntimeException("Cookie recipe does not exist");
    }

    @Override
    public boolean addCookie(Cookie cookie) {
        if (acceptRecipe(cookie)) {
            catalogRepository.save(cookie, cookie.getId());
            return true;
        }
        return false;
    }

    @Override
    public void removeCookie(Cookie cookie) {
        catalogRepository.deleteById(cookie.getId());
    }

    @Override
    public boolean acceptRecipe(Cookie c) {
        return c.getPrice() < 7.5;
    }

    @Override
    public List<Cookie> getCookies() {
        Iterable<Cookie> cookies = catalogRepository.findAll();
        if (cookies == null) {
            return new ArrayList<>();
        }
        return StreamSupport.stream(cookies.spliterator(), false).toList();
    }


    @Override
    public List<Cookie> findByName(String name) {
        return StreamSupport.stream(catalogRepository.findAll().spliterator(), false)
                .filter(cookie -> name.equals(cookie.getName())).toList();
    }

}
