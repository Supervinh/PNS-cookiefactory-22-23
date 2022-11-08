package fr.unice.polytech.cf;

import fr.unice.polytech.cf.ingredients.*;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private final List<Cookie> cookies;

    public Catalog() {
        this.cookies = new ArrayList<>();
        initCatalog();
    }

    private void initCatalog() {
        this.cookies.add(new Cookie("chocolate", Cooking.CRUNCHY, Dough.CHOCOLATE, Flavour.CINNAMON, Mix.MIXED, new ArrayList<>()));
        this.cookies.add(new Cookie("caramel", Cooking.CRUNCHY, Dough.PLAIN, Flavour.VANILLA, Mix.MIXED, new ArrayList<>()));
        ArrayList<IngredientTest> toppings = new ArrayList<>();
        this.cookies.add(new Cookie("Chocolate Chip", Cooking.CRUNCHY, new IngredientTest(IngredientEnum.DOUGH, "Chocolat", 3),
                new IngredientTest(IngredientEnum.FLAVOUR, "Cinnamon", 2.5), Mix.MIXED, toppings));
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
