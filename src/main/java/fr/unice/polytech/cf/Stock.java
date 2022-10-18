package fr.unice.polytech.cf;

/**
 * Inspired from group e in 20-21
 *
 * @author Mireille Blay
 * @version %I% %G%
 */
public class Stock {

    public int getAmount() {
        return amount;
    }

    private int amount;

    public Stock(int amount) {
        this.amount = amount;
    }


    /**
     * modify pAmount to the stock.
     * When pAmount is negative, the amount is retracted.
     *
     * @param   pAmount
     * @return  <code>false</code> if it is a withdrawal and the stock is insufficient
     *          <code>true</code> otherwise.
     */
    public boolean modifyAmount(int pAmount) {
        int newAmount = amount + pAmount;
        if (newAmount < 0) {
            return false;
        }
        amount = newAmount;
        return true;
    }
}
