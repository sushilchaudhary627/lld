package models;

import constants.SlotStatus;

import java.time.LocalDateTime;

public class Slot {
    private final Integer slotId;
    private final Doctor doctor;
    private SlotStatus slotStatus;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    public Slot(Integer slotId, Doctor doctor) {
        this.slotId = slotId;
        this.doctor = doctor;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public SlotStatus getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(SlotStatus slotStatus) {
        this.slotStatus = slotStatus;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }

    public boolean isAvailable(){
        return slotStatus == SlotStatus.AVAILABLE;
    }

    public boolean isOverlapping(Slot slot){
        return !(slot.getEndAt().isBefore(startAt) || slot.getStartAt().isAfter(endAt));

    }
}
