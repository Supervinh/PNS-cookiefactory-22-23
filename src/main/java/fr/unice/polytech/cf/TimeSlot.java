package fr.unice.polytech.cf;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

class TimeSlot implements Comparable<TimeSlot> {
    private LocalDateTime startSlot;
    private LocalDateTime endSlot;

    public LocalDateTime getStartSlot() {
        return startSlot;
    }

    public LocalDateTime getEndSlot() {
        return endSlot;
    }

    TimeSlot(LocalDateTime date){
        int d = date.getHour()*60+date.getMinute();
        startSlot = date.minusMinutes(d%15).minusSeconds(date.getSecond()).minusNanos(date.getNano());
        endSlot = date.plusMinutes(15-d%15).minusSeconds(date.getSecond()).minusNanos(date.getNano());
    }

    public static List<TimeSlot> orderToTimeSlot(Order o){
        ArrayList<TimeSlot> ret = new ArrayList<TimeSlot>();
        LocalDateTime d = o.getRetrieveDate();
        int cookingTime = o.getCookingTime();
        while(cookingTime > 0){
            ret.add(new TimeSlot(d.minusMinutes(cookingTime)));
            cookingTime-=15;
        }
        return ret;
    }

    @Override
    public int compareTo(TimeSlot o) {
        return startSlot.compareTo(o.startSlot); // every time slot should be 15 mins so comparing only starting time is enough
    }
}