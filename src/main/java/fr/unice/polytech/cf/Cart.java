package fr.unice.polytech.cf;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Cookie> cookies;

    public Cart() {
        this.cookies = new ArrayList<>();
    }

    public void addCookie(Cookie cookie) {
        this.cookies.add(cookie);
    }
}
