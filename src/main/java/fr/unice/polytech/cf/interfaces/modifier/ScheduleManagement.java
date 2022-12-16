package fr.unice.polytech.cf.interfaces.modifier;

import fr.unice.polytech.cf.entities.Cook;
import fr.unice.polytech.cf.entities.Order;
import fr.unice.polytech.cf.entities.TimeSlot;

import java.util.List;

public interface ScheduleManagement {

    boolean isAvailable(Cook cook, TimeSlot timeSlot);

    List<TimeSlot> orderToTimeSlot(Order order);

    boolean addOrder(Cook cook, Order order);
}
