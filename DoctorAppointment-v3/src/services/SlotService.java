package services;

import constants.SlotStatus;
import models.Doctor;
import models.Slot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SlotService {
    private final AtomicInteger slotIdGenerator;

    public SlotService(AtomicInteger slotIdGenerator) {
        this.slotIdGenerator = slotIdGenerator;
    }

    public List<Slot> createSlots(Doctor doctor, List<LocalTime>slotStartTimes, LocalDate date){
        List<LocalTime>sortedStartTimes = slotStartTimes.stream().sorted(Comparator.naturalOrder()).toList();
        System.out.println(slotStartTimes);
        List<Slot>slots = new ArrayList<>();
        for(LocalTime slotStartTime: sortedStartTimes){
            LocalDateTime slotStart = date.atTime(slotStartTime);
            LocalDateTime slotEnd = date.atTime(slotStartTime.plusMinutes(30));
            Slot slot = new Slot(slotIdGenerator.getAndIncrement(), doctor);
            slot.setSlotStatus(SlotStatus.AVAILABLE);
            slot.setStartAt(slotStart);
            slot.setEndAt(slotEnd);
            if(!slots.isEmpty() && slot.isOverLapping(slots.getLast())){
                throw new RuntimeException("Slot is overlapping");
            }
            slots.add(slot);
        }
        return slots;
    }

    public void updateSlotIsAvailable(Slot slot){
        slot.setSlotStatus(SlotStatus.AVAILABLE);
    }

    public void updateSlotIsBooked(Slot slot){
        slot.setSlotStatus(SlotStatus.BOOKED);
    }
}
