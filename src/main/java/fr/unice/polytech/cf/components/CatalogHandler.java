package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.cookies.BasicCookie;
import fr.unice.polytech.cf.entities.cookies.PartyCookie;
import fr.unice.polytech.cf.entities.ingredients.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CatalogHandler {
    private final List<BasicCookie> basicCookies;

    public CatalogHandler() {
        this.basicCookies = new ArrayList<>();
        initCatalog();
    }

    public CatalogHandler(StockHandler stock) {
        this.basicCookies = new ArrayList<>();
        updateCatalog(stock);
    }

    public void updateCatalog(StockHandler stock) {
        initCatalog();
        basicCookies.removeIf(c -> !stock.canBeRemoved(c));
    }

    private void initCatalog() {
        this.basicCookies.clear();
        ArrayList<Ingredient> toppings = new ArrayList<>();
        this.basicCookies.add(new BasicCookie("chocolate", Cooking.CRUNCHY,
                new Ingredient(storeId, IngredientEnum.DOUGH, "Chocolate", 3),
                new Ingredient(storeId, IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, toppings));
        this.basicCookies.add(new BasicCookie("caramel", Cooking.CRUNCHY,
                new Ingredient(storeId, IngredientEnum.DOUGH, "Plain", 2.2),
                new Ingredient(storeId, IngredientEnum.FLAVOUR, "Vanilla", 2),
                Mix.MIXED, toppings));
        this.basicCookies.add(new PartyCookie("XLcaramel", Cooking.CRUNCHY,
                new Ingredient(storeId, IngredientEnum.DOUGH, "Plain", 2.2),
                new Ingredient(storeId, IngredientEnum.FLAVOUR, "Vanilla", 2),
                Mix.MIXED, toppings, 5, "birthday", "fairies"));
    }


    public BasicCookie getCookie(String name) {
        for (BasicCookie c : this.basicCookies) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        throw new RuntimeException("Cookie recipe does not exist");
    }

    public void addCookie(BasicCookie basicCookie) {
        this.basicCookies.add(basicCookie);
    }

    public void removeCookie(BasicCookie basicCookie) {
        this.basicCookies.remove(basicCookie);
    }

    public List<BasicCookie> getCookies() {
        return new ArrayList<>(this.basicCookies);
    }

    public boolean hasCookie(String name) {
        for (BasicCookie basicCookie : this.basicCookies) {
            if (basicCookie.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
