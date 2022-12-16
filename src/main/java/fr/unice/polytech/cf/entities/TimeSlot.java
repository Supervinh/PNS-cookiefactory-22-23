package fr.unice.polytech.cf.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimeSlot implements Comparable<TimeSlot> {
    private LocalDateTime startSlot;
    private LocalDateTime endSlot;

    public LocalDateTime getStartSlot() {
        return startSlot;
    }

    public LocalDateTime getEndSlot() {
        return endSlot;
    }

    public TimeSlot(LocalDateTime date){
        int d = date.getHour()*60+date.getMinute();
        startSlot = date.minusMinutes(d%15).minusSeconds(date.getSecond()).minusNanos(date.getNano());
        endSlot = date.plusMinutes(15-d%15).minusSeconds(date.getSecond()).minusNanos(date.getNano());
    }

    @Override
    public int compareTo(TimeSlot o) {
        return startSlot.compareTo(o.startSlot); // every time slot should be 15 mins so comparing only starting time is enough
    }
}