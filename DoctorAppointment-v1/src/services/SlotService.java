package services;

import constanta.SlotStatus;
import models.Slot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class SlotService {
    private final Integer SLOT_DURATION_IN_MIN = 30;
    private final AtomicLong slotIdGenerator;

    public SlotService(AtomicLong slotIdGenerator) {
        this.slotIdGenerator = slotIdGenerator;
    }

    public List<Slot> createSlots(List<LocalTime>slotStartingTimes, LocalDate date) {
        List<LocalTime>slotStartingTimesSorted = slotStartingTimes.stream().sorted().toList();
        List<Slot>slots = new ArrayList<>();
        for(LocalTime startTime: slotStartingTimesSorted){
            LocalTime endTime = startTime.plusMinutes(SLOT_DURATION_IN_MIN);
            Slot slot = new Slot(slotIdGenerator.getAndIncrement());
            slot.setSlotStatus(SlotStatus.AVAILABLE);
            slot.setSlotStart(date.atTime(startTime));
            slot.setSlotEnd(date.atTime(endTime));
            slots.add(slot);
        };
        validSlotsOverlapping(slots);
        System.out.println(slots);
        return slots;
    }

    public void markSlotBooked(Slot slot){
        slot.setSlotStatus(SlotStatus.BOOK);
    }

    public void markSlotAvailable(Slot slot){
        slot.setSlotStatus(SlotStatus.AVAILABLE);
    }

    void validSlotsOverlapping(List<Slot>slots){
        for(int i = 0; i<slots.size()-1; i++){
                Slot slot1 = slots.get(i);
                Slot slot2 = slots.get(i+1);
                if (!slot1.getSlotEnd().isBefore(slot2.getSlotStart())) {
                    throw new RuntimeException("Slots are overlapping...");
                }
        }
    }
}
