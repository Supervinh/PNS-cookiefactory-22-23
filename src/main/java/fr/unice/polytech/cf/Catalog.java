package fr.unice.polytech.cf;

import fr.unice.polytech.cf.ingredients.Cooking;
import fr.unice.polytech.cf.ingredients.Dough;
import fr.unice.polytech.cf.ingredients.Flavour;
import fr.unice.polytech.cf.ingredients.Mix;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private List<Cookie> cookies;

    public Catalog() {
        this.cookies = new ArrayList<>();
        initCatalog();
    }

    private void initCatalog() {
        this.cookies.add(new Cookie("Chocolate Chip", Cooking.CRUNCHY, Dough.CHOCOLATE, Flavour.CINNAMON, Mix.MIXED, new ArrayList<>()));
    }

    public void addCookie(Cookie cookie){
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
