package fr.unice.polytech.cf;

import fr.unice.polytech.cf.ingredients.*;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private final List<Cookie> cookies;
    private Stock stock;

    public Catalog() {
        this.cookies = new ArrayList<>();
        this.stock = new Stock();
        initCatalog();
    }

    public Catalog(Stock stock) {
        this.cookies = new ArrayList<>();
        this.stock = stock;
        initCatalog();
    }

    private void initCatalog() {
        ArrayList<Ingredient> toppings = new ArrayList<>();
        this.cookies.add(new Cookie("chocolate", Cooking.CRUNCHY,
                stock.getIngredients().stream().filter(i -> i.getName().equals("Chocolate")).findFirst().get(),
                stock.getIngredients().stream().filter(i -> i.getName().equals("Cinnamon")).findFirst().get(),
                Mix.MIXED, toppings));
        this.cookies.add(new Cookie("caramel", Cooking.CRUNCHY,
                stock.getIngredients().stream().filter(i -> i.getName().equals("Plain")).findFirst().get(),
                stock.getIngredients().stream().filter(i -> i.getName().equals("Vanilla")).findFirst().get(),
                Mix.MIXED, toppings));

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
