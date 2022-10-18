package fr.unice.polytech.cf;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Cookie> cookies;

    public Cart(List<Cookie> cookies) {
        this.cookies = new ArrayList<Cookie>();
        this.cookies = cookies;

    }

    public void addCookie(Cookie cookie) {
        this.cookies.add(cookie);
    }
}
