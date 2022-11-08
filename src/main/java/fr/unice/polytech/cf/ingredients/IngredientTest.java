package fr.unice.polytech.cf.ingredients;

public class IngredientTest {

    private IngredientEnum type;
    private String name;
    private double price;

    public IngredientTest(String type, String name, double price) {
        this.type = IngredientEnum.valueOf(type);
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addTaxe(double taxe){
        this.price += taxe;
    }

    public String getName() {
        return name;
    }
}
