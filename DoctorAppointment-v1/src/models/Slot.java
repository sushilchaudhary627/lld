package models;

import constanta.SlotStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Slot {
    private final Long id;
    private SlotStatus slotStatus;
    private LocalDateTime slotStart;
    private LocalDateTime slotEnd;

    public Slot(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public SlotStatus getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(SlotStatus slotStatus) {
        this.slotStatus = slotStatus;
    }

    public LocalDateTime getSlotStart() {
        return slotStart;
    }

    public void setSlotStart(LocalDateTime slotStart) {
        this.slotStart = slotStart;
    }

    public LocalDateTime getSlotEnd() {
        return slotEnd;
    }

    public void setSlotEnd(LocalDateTime slotEnd) {
        this.slotEnd = slotEnd;
    }

    @Override
    public String toString() {
        return "\nSlot{" +
            "id=" + id +
            ", slotStatus=" + slotStatus +
            ", slotStart=" + slotStart +
            ", slotEnd=" + slotEnd +
            '}';
    }

    public boolean isAvailable(){
        return SlotStatus.AVAILABLE == slotStatus;
    }
}
