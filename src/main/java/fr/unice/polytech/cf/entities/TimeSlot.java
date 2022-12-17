package fr.unice.polytech.cf.entities;

import java.time.LocalDateTime;

public class TimeSlot implements Comparable<TimeSlot> {
    private final LocalDateTime startSlot;
    private final LocalDateTime endSlot;

    public TimeSlot(LocalDateTime date) {
        int d = date.getHour() * 60 + date.getMinute();
        startSlot = date.minusMinutes(d % 15).minusSeconds(date.getSecond()).minusNanos(date.getNano());
        endSlot = date.plusMinutes(15 - d % 15).minusSeconds(date.getSecond()).minusNanos(date.getNano());
    }

    public LocalDateTime getStartSlot() {
        return startSlot;
    }

    public LocalDateTime getEndSlot() {
        return endSlot;
    }

    @Override
    public int compareTo(TimeSlot o) {
        return startSlot.compareTo(o.startSlot); // every time slot should be 15 mins so comparing only starting time is enough
    }
}