package models;

import constants.SlotStatus;

import java.time.LocalDateTime;

public class Slot {
    private final Integer slotId;
    private final Doctor doctor;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private SlotStatus slotStatus;


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

    public SlotStatus getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(SlotStatus slotStatus) {
        this.slotStatus = slotStatus;
    }

    public boolean isAvailable(){
        return slotStatus == SlotStatus.AVAILABLE;
    }

    public boolean isOverLapping(Slot slot){
        return !(endAt.isBefore(slot.getStartAt()) || slot.getEndAt().isBefore(startAt));
    }

    @Override
    public String toString() {
        return "Slot{" +
            "slotId=" + slotId +
            ", doctor=" + doctor.getName() +
            ", startAt=" + startAt +
            ", endAt=" + endAt +
            ", slotStatus=" + slotStatus +
            '}';
    }
}
