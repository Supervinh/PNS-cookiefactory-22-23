package fr.unice.polytech.cf;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private List<Cookie> cookies;

    public Catalog() {
        this.cookies = new ArrayList<>();
        this.cookies.add(new Cookie("Chocolate"));
    }

    public List<Cookie> getCookies() {
        return this.cookies;
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
