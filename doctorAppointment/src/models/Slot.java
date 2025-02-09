package models;

import java.time.LocalDateTime;

public class Slot {
    private final Integer scheduleId;
    private final Doctor doctor;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private ScheduleStatus scheduleStatus;

    public Slot(Integer scheduleId, Doctor doctor) {
        this.scheduleId = scheduleId;
        this.doctor = doctor;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public ScheduleStatus getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(ScheduleStatus scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    @Override
    public String toString(){
        return "Slot("+ scheduleId+")" + "will start at " + startAt.toLocalTime() + " will end "+ endAt.toLocalTime();
    }
}
