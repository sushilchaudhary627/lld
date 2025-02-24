package services;

import constants.SlotStatus;
import models.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SlotService {
    private final AtomicInteger slotIdGenerator;

    public SlotService(AtomicInteger slotIdGenerator) {
        this.slotIdGenerator = slotIdGenerator;
    }

    List<Slot> createSlots(List<LocalTime> slotStartTimes, Doctor doctor, LocalDate date){
        List<Slot>slots = new ArrayList<>();
        for(LocalTime startTime: slotStartTimes){
            LocalDateTime slotStart = date.atTime(startTime);
            LocalDateTime slotEnd = date.atTime(startTime.plusMinutes(30));
            Slot slot = new Slot(slotIdGenerator.getAndIncrement(), doctor);
            slot.setStartAt(slotStart);
            slot.setEndAt(slotEnd);
            slot.setSlotStatus(SlotStatus.AVAILABLE);
            if(!slots.isEmpty() && slot.isOverlapping(slots.getFirst())){
                throw new RuntimeException("Slots are overlapping.");
            }
            slots.add(slot);
            System.out.printf("Slot with start: %s and end: %s is created succesfully\n", slot.getStartAt(), slot.getEndAt());
        }
        return slots;
    }

    public void setSlotIsAvailable(Slot slot){
        slot.setSlotStatus(SlotStatus.AVAILABLE);
    }

    public void setSlotIsBooked(Slot slot){
        slot.setSlotStatus(SlotStatus.BOOKED);
    }
}
