package fr.unice.polytech.cf.ingredients;
public enum Topping {
    WHITECHOCOLATE(1.5),
    MILKCHOCOLATE(2.5),
    MMS(3.5),
    REESESBUTTERCUP(1.5);

    private double price;

    Topping(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static double getPrice(String topping) {
        for (Dough t : Dough.values()) {
            if (t.name().equals(topping)) {
                return t.getPrice();
            }
        }
        return 0;
    }
}