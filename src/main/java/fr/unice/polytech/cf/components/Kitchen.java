package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.Cook;
import fr.unice.polytech.cf.entities.Order;
import fr.unice.polytech.cf.entities.OrderState;
import fr.unice.polytech.cf.entities.Store;
import fr.unice.polytech.cf.interfaces.OrderProcessing;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class Kitchen implements OrderProcessing {
    private final CookScheduler cookScheduler;

    public Kitchen(CookScheduler cookScheduler) {
        this.cookScheduler = cookScheduler;
    }

    @Override
    public boolean assignOrder(Order o, Store store) {
        List<Cook> cooks = cookScheduler.getCooksByStoreId(store.getId());
        for (Cook c : cooks) {
            if (cookScheduler.addOrder(c, o)) {
                prepareOrder(o);
                return true;
            }
        }
        return false;
    }

    @Override
    public void prepareOrder(Order o) {
        if (o.getOrderState() == OrderState.PAID) {
            o.setOrderState(OrderState.WORKING_ON_IT);
        }
    }

    @Override
    public void finishOrder(Order o) {
        if (o.getOrderState() == OrderState.WORKING_ON_IT) {
            o.setOrderState(OrderState.READY);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Your order is ready, you can comme pick it up !!");
                }
            }, 1000 * 60 * 5);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("We are keeping your order for 1 more hour, after that it will be thrown away");
                }
            }, 1000 * 60 * 60);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("We are sorry but your order is now cold and we can't give it to you anymore");
                    o.setOrderState(OrderState.TOO_GOOD_TO_GO);
                }
            }, 1000 * 60 * 120);
        }
    }
}
