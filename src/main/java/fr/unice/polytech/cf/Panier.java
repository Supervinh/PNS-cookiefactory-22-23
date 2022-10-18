package fr.unice.polytech.cf;

import java.util.List;

public class Panier {
    private int price;
    private List<Item> cookies;

    public Panier(List<Item> cookies) {
        this.cookies = cookies;
        this.price = 0;
        for (Item item : cookies) {
            this.price += item.getAmount() * item.getIngredient().getPrice();
        }
    }

    public void addCookie(Item cookie) {
        this.cookies.add(cookie);
        this.price += cookie.getIngredient().getPrice();
    }
}
