package fr.unice.polytech.cf.entities;

import java.time.LocalTime;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

public class Cook {

    private final String name;
    private final SortedMap<TimeSlot, Order> cookSchedule;
    private final boolean canMakePartyCookie;
    private final int startWork;
    private final int endWork;
    private final UUID id;
    private final UUID storeId;

    public Cook(String name, LocalTime startWork, LocalTime endWork, UUID storeId) {
        this.name = name;
        this.startWork = startWork.getHour() * 60 + startWork.getMinute();
        this.endWork = endWork.getHour() * 60 + endWork.getMinute();
        cookSchedule = new TreeMap<>();
        this.canMakePartyCookie = false;
        this.id = UUID.randomUUID();
        this.storeId = storeId;
    }

    public Cook(String name, LocalTime startWork, LocalTime endWork, boolean canMakePartyCookie, UUID storeId) {
        this.name = name;
        this.startWork = startWork.getHour() * 60 + startWork.getMinute();
        this.endWork = endWork.getHour() * 60 + endWork.getMinute();
        cookSchedule = new TreeMap<>();
        this.canMakePartyCookie = canMakePartyCookie;
        this.id = UUID.randomUUID();
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public int getStartWork() {
        return startWork;
    }

    public int getEndWork() {
        return endWork;
    }

    public SortedMap<TimeSlot, Order> getCookSchedule() {
        return cookSchedule;
    }

    public UUID getId() {
        return id;
    }

    public UUID getStoreId() {
        return storeId;
    }

    public boolean canMakePartyCookie() {
        return canMakePartyCookie;
    }

}
