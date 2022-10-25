package fr.unice.polytech.cf.ingredients;
public enum Flavour {
    VANILLA(2),
    CINNAMON(2.5),
    CHILI(3);

    private double price;

    Flavour(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static double getPrice(String flavor) {
        for (Flavour f : Flavour.values()) {
            if (f.name().equals(flavor)) {
                return f.getPrice();
            }
        }
        return 0;
    }
}