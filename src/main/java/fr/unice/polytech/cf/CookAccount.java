package fr.unice.polytech.cf;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class CookAccount {

    private String name;
    private int startwork;

    public void setStartwork(int startwork) {
        this.startwork = startwork;
    }

    public void setEndwork(int endwork) {
        this.endwork = endwork;
    }

    private int endwork;

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
    private boolean canMakePartyCookie;

    private SortedMap<TimeSlot, Order> cookSchedule;

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

    boolean addOrder(Order o){
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

    void prepareOrder(Order o){
        if(o.getOrderState() == OrderState.PAID){
            o.setOrderState(OrderState.WORKING_ON_IT);
        }
    }

    void finishOrder(Order o){
        if(o.getOrderState() == OrderState.WORKING_ON_IT){
            o.setOrderState(OrderState.READY);
        }
    }


    public boolean canMakePartyCookie(){return canMakePartyCookie;}
}
