package fr.unice.polytech.cf.ingredients;
public enum Dough implements Ingredient {
    PLAIN(2.2),
    CHOCOLATE(3),
    PEANUTBUTTER(1.5),
    OATMEAL(2);

    private double price;

    Dough(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice(String dough) {
        for (Dough d : Dough.values()) {
            if (d.name().equals(dough)) {
                return d.getPrice();
            }
        }
        return 0;
    }
}