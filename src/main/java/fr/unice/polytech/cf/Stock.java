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
        doughStock.put(Dough.PLAIN, 20);
        doughStock.put(Dough.CHOCOLATE, 20);
        doughStock.put(Dough.PEANUTBUTTER, 20);
        doughStock.put(Dough.OATMEAL, 20);

        flavourStock.put(Flavour.VANILLA, 20);
        flavourStock.put(Flavour.CINNAMON, 20);
        flavourStock.put(Flavour.CHILI, 20);

        toppingStock.put(Topping.WHITECHOCOLATE, 20);
        toppingStock.put(Topping.MILKCHOCOLATE, 20);
        toppingStock.put(Topping.MMS, 20);
        toppingStock.put(Topping.REESESBUTTERCUP, 20);
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
