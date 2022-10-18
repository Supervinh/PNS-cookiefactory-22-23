package fr.unice.polytech.cf;

public class Cookie {
    private final String name;
    private final double price;

    public Cookie(String name) {
        this.name = name;
        this.price = 5;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
