package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private  final  Integer doctorId;
    private String name;
    private  Speciality speciality;
    private  final LocalDateTime createdAt;
    private List<Slot>slotsForToday;

    public Doctor(Integer doctorId, String name, Speciality speciality, LocalDateTime createdAt) {
        this.doctorId = doctorId;
        this.name = name;
        this.speciality = speciality;
        this.createdAt = createdAt;
        this.slotsForToday = new ArrayList<>();
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public String getName() {
        return name;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public List<Slot> getSlotsForToday(){
        return slotsForToday;
    }

    public void addSlot(Slot slot){
        slotsForToday.add(slot);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", name='" + name + '\'' +
                ", speciality=" + speciality +
                ", createdAt=" + createdAt +
                ", slotsForToday=" + slotsForToday +
                '}';
    }

    public boolean hasOverlappingSlot(LocalDateTime slotStart, LocalDateTime slotEnd) {
        for(Slot slot:slotsForToday){
            if (slot.getEndAt().isAfter(slotStart) && slot.getStartAt().isBefore(slotEnd)) {
                return true;
            }
        }
        return false;
    }
}
