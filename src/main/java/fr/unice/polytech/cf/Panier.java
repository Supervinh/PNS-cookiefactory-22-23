package fr.unice.polytech.cf;

import java.util.List;

public class Panier {
    private int price;
    private List<Cookie> cookies;

    public Panier(List<Cookie> cookies) {
        this.cookies = cookies;
        this.price = 0;
        for (Cookie cookie : cookies) {
            this.price += cookie.getAmount() * cookie.getIngredient().getPrice();
        }
    }

    public void addCookie(Cookie cookie) {
        this.cookies.add(cookie);
        this.price += cookie.getIngredient().getPrice();
    }
}
