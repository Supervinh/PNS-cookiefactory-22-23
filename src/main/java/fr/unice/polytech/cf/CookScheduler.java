package fr.unice.polytech.cf;

import fr.unice.polytech.cf.entities.Order;

import java.time.LocalTime;
import java.util.List;

public class CookScheduler{

    List<CookAccount> cooks;
    int openingTime;
    int closingTime;

    public List<CookAccount> getCooks() {
        return cooks;
    }

    public int getOpeningTime() {
        return openingTime;
    }

    public int getClosingTime() {
        return closingTime;
    }



    CookScheduler(LocalTime openingtime, LocalTime closingtime, List<CookAccount> cooks){
        openingTime = openingtime.getHour()*60+openingtime.getMinute();
        closingTime = closingtime.getHour()*60+closingtime.getMinute();
        this.cooks = cooks;
    }

    void addcook(CookAccount newcook){
        cooks.add(newcook);
    }
    void addcook(List<CookAccount> newcook){cooks.addAll(newcook);}

    void removecook(CookAccount cook){
        cooks.remove(cook);
    }

    boolean assignOrder(Order o){
        for(CookAccount c : cooks){
            if(c.addOrder(o)){return true;}
        }
        return false;
    }

}