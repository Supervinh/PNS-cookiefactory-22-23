package fr.unice.polytech.cf.entities;

import fr.unice.polytech.cf.entities.Order;
import fr.unice.polytech.cf.entities.OrderState;
import fr.unice.polytech.cf.entities.TimeSlot;

import java.time.LocalTime;
import java.util.*;

public class CookAccount {

    private String name;
    private int startwork;
    private int endwork;
    private SortedMap<TimeSlot, Order> cookSchedule;
    private boolean canMakePartyCookie;

    public void setStartwork(int startwork) {
        this.startwork = startwork;
    }

    public void setEndwork(int endwork) {
        this.endwork = endwork;
    }



    public String getName() {
        return name;
    }

    public int getStartwork() {
        return startwork;
    }

    public int getEndwork() {
        return endwork;
    }

    public SortedMap<TimeSlot, Order> getCookSchedule() {
        return cookSchedule;
    }




    public CookAccount(String name, LocalTime startwork, LocalTime endwork) {
        this.name = name;
        this.startwork = startwork.getHour() * 60 + startwork.getMinute();
        this.endwork = endwork.getHour() * 60 + endwork.getMinute();
        cookSchedule = new TreeMap<>();
        canMakePartyCookie = false;
    }
    public CookAccount(String name, LocalTime startwork, LocalTime endwork, boolean canMakePartyCookie) {
        this.name = name;
        this.startwork = startwork.getHour() * 60 + startwork.getMinute();
        this.endwork = endwork.getHour() * 60 + endwork.getMinute();
        cookSchedule = new TreeMap<>();
        this.canMakePartyCookie = canMakePartyCookie;
    }

    boolean isFree(TimeSlot ts){
        if(ts.getStartSlot().getHour()*60+ts.getStartSlot().getMinute() < startwork){ // timeSlot is before work hours
            return false;
        }
        if(ts.getEndSlot().getHour()*60+ts.getEndSlot().getMinute() > endwork){ // timeSlot is after work hours
            return false;
        }
        for(TimeSlot timeslots : cookSchedule.keySet()){ // timeslot is already occupied by another order
            //timeslot are meant to be synchronyzed and thus result of nexts comparison should always be 0 when another timeslot exists
            if (ts.getStartSlot().compareTo(timeslots.getStartSlot()) >= 0 && ts.getStartSlot().compareTo(timeslots.getEndSlot()) <=0){
                return false;
            }
        }
        return true;
    }

    public boolean addOrder(Order o){
        List<TimeSlot> tslist = TimeSlot.orderToTimeSlot(o);

        for(TimeSlot ts1 : tslist){
            if(!isFree(ts1)){
                return false;
            }
        }
        for(TimeSlot ts1 : tslist){
            cookSchedule.put(ts1, o);
        }
        return true;
    }

    public void prepareOrder(Order o){
        if(o.getOrderState() == OrderState.PAID){
            o.setOrderState(OrderState.WORKING_ON_IT);
        }
    }

    public void finishOrder(Order o){
        if(o.getOrderState() == OrderState.WORKING_ON_IT){
            o.setOrderState(OrderState.READY);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Your order is ready, you can comme pick it up !!");
                }
            }, 1000*60*5);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("We are keeping your order for 1 more hour, after that it will be thrown away");
                }
            }, 1000*60*60);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("We are sorry but your order is now cold and we can't give it to you anymore");
                    o.setOrderState(OrderState.TOO_GOOD_TO_GO);
                }
            }, 1000*60*120);
        }
    }


    public boolean canMakePartyCookie(){return canMakePartyCookie;}
}
