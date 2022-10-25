package fr.unice.polytech.cf;

import fr.unice.polytech.cf.ingredients.Dough;
import fr.unice.polytech.cf.ingredients.Flavour;
import fr.unice.polytech.cf.ingredients.Topping;

import java.util.Map;

public class Stock {

    /*public int getAmount() {
        return amount;
    }

    private int amount;*/

    private Map<Dough, Integer> doughStock;
    private Map<Flavour, Integer> flavourStock;
    private Map<Topping, Integer> toppingStock;


    public Stock() {
        initStock();
    }

    private void initStock(){
        doughStock.put(new Dough("Plain", 2.2), 20);
        doughStock.put(new Dough("Chocolate", 3), 20);
        doughStock.put(new Dough("Peanut butter", 1.5), 20);
        doughStock.put(new Dough("Oatmeal", 2), 20);

        flavourStock.put(new Flavour("Vanilla", 2), 20);
        flavourStock.put(new Flavour("Cinnamon", 2.5), 20);
        flavourStock.put(new Flavour("Chili", 3), 20);

        toppingStock.put(new Topping("White Chocolate", 1.5), 20);
        toppingStock.put(new Topping("Milk Chocolate", 2.5), 20);
        toppingStock.put(new Topping("M&M’s", 3.5), 20);
        toppingStock.put(new Topping(" Reese’s buttercup", 1.5), 20);
    }


    /**
     * modify pAmount to the stock.
     * When pAmount is negative, the amount is retracted.
     *
     * @param   pAmount
     * @return  <code>false</code> if it is a withdrawal and the stock is insufficient
     *          <code>true</code> otherwise.
     */
    /*public boolean modifyAmount(int pAmount) {
        int newAmount = amount + pAmount;
        if (newAmount < 0) {
            return false;
        }
        amount = newAmount;
        return true;
    }*/
}
