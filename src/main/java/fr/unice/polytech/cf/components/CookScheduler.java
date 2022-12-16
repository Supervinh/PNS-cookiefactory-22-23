package fr.unice.polytech.cf.components;

import fr.unice.polytech.cf.entities.Cook;
import fr.unice.polytech.cf.entities.Order;
import fr.unice.polytech.cf.entities.TimeSlot;
import fr.unice.polytech.cf.exceptions.AlreadyExistingCustomerException;
import fr.unice.polytech.cf.interfaces.CookFinder;
import fr.unice.polytech.cf.interfaces.CookRegistration;
import fr.unice.polytech.cf.interfaces.ScheduleManagement;
import fr.unice.polytech.cf.repositories.CookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class CookScheduler implements ScheduleManagement, CookFinder, CookRegistration {

    private final CookRepository cookRepository;

    @Autowired
    public CookScheduler(CookRepository cookRepository) {
        this.cookRepository = cookRepository;
    }

    @Override
    public Iterable<Cook> getCooks() {
        return cookRepository.findAll();
    }

    @Override
    public List<Cook> getCooksByStoreId(UUID storeId) {
        return StreamSupport.stream(cookRepository.findAll().spliterator(), false)
                .filter(cook -> cook.getStoreId().equals(storeId))
                .collect(Collectors.toList());
    }


    @Override
    public Cook addCook(String name, LocalTime startWork, LocalTime endWork, UUID storeId) throws AlreadyExistingCustomerException {
        if(findByName(name).isPresent())
            throw new AlreadyExistingCustomerException(name);
        Cook newCook = new Cook(name, startWork, endWork, storeId);
        cookRepository.save(newCook, newCook.getId());
        return newCook;
    }

    public Optional<Cook> findByName(String name) {
        return StreamSupport.stream(cookRepository.findAll().spliterator(), false)
                .filter(cook -> name.equals(cook.getName())).findAny();
    }

    @Override
    public List<Cook> addCooks(List<Cook> newCooks) {
        for (Cook c : newCooks) {
            cookRepository.save(c, c.getId());
        }
        return newCooks;
    }

    @Override
    public void removeCook(Cook cook) {
        cookRepository.deleteById(cook.getId());
    }

    @Override
    public boolean addOrder(Cook cook, Order o) {
        List<TimeSlot> tslist = orderToTimeSlot(o);

        for (TimeSlot ts1 : tslist) {
            if (!isAvailable(cook, ts1)) {
                return false;
            }
        }
        for (TimeSlot ts1 : tslist) {
            cook.getCookSchedule().put(ts1, o);
        }
        return true;
    }

    @Override
    public List<TimeSlot> orderToTimeSlot(Order o) {
        ArrayList<TimeSlot> ret = new ArrayList<TimeSlot>();
        LocalDateTime d = o.getRetrieveDate();
        double cookingTime = o.getCookingTime();
        while (cookingTime > 0) {
            ret.add(new TimeSlot(d.minusMinutes((long) cookingTime)));
            cookingTime -= 15;
        }
        return ret;
    }

    @Override
    public boolean isAvailable(Cook cook, TimeSlot ts) {
        if (ts.getStartSlot().getHour() * 60 + ts.getStartSlot().getMinute() < cook.getStartWork()) { // timeSlot is before work hours
            return false;
        }
        if (ts.getEndSlot().getHour() * 60 + ts.getEndSlot().getMinute() > cook.getEndWork()) { // timeSlot is after work hours
            return false;
        }
        for (TimeSlot timeslots : cook.getCookSchedule().keySet()) { // timeslot is already occupied by another order
            //timeslot are meant to be synchronized and thus result of next comparison should always be 0 when another timeslot exists
            if (ts.getStartSlot().compareTo(timeslots.getStartSlot()) >= 0 && ts.getStartSlot().compareTo(timeslots.getEndSlot()) <= 0) {
                return false;
            }
        }
        return true;
    }

}