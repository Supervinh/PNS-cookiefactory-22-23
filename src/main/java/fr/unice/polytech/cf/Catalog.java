package fr.unice.polytech.cf;

import fr.unice.polytech.cf.cookies.Cookie;
import fr.unice.polytech.cf.cookies.PartyCookie;
import fr.unice.polytech.cf.ingredients.*;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private final List<Cookie> cookies;

    public Catalog() {
        this.cookies = new ArrayList<>();
        initCatalog();
    }

    public Catalog(Stock stock) {
        this.cookies = new ArrayList<>();
        initCatalog();
    }

    private void initCatalog() {
        ArrayList<Ingredient> toppings = new ArrayList<>();
        this.cookies.add(new Cookie("chocolate", Cooking.CRUNCHY,
                new Ingredient(IngredientEnum.DOUGH, "Chocolate", 3),
                new Ingredient(IngredientEnum.FLAVOUR, "Cinnamon", 2.5),
                Mix.MIXED, toppings));
        this.cookies.add(new Cookie("caramel", Cooking.CRUNCHY,
                new Ingredient(IngredientEnum.DOUGH, "Plain", 2.2),
                new Ingredient(IngredientEnum.FLAVOUR, "Vanilla", 2),
                Mix.MIXED, toppings));
        this.cookies.add(new PartyCookie("XLcaramel", Cooking.CRUNCHY,
                new Ingredient(IngredientEnum.DOUGH, "Plain", 2.2),
                new Ingredient(IngredientEnum.FLAVOUR, "Vanilla", 2),
                Mix.MIXED, toppings, 5, "birthday", "fairies"));
    }


    public Cookie getCookie(String name) {
        for (Cookie c : this.cookies) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        throw new RuntimeException("Cookie recipe does not exist");
    }

    public void addCookie(Cookie cookie) {
        this.cookies.add(cookie);
    }

    public void removeCookie(Cookie cookie) {
        this.cookies.remove(cookie);
    }

    public List<Cookie> getCookies() {
        return new ArrayList<>(this.cookies);
    }

    public boolean hasCookie(String name) {
        for (Cookie cookie : this.cookies) {
            if (cookie.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
