package models;

import constants.Speciality;

import java.util.List;

public class Doctor {
    private final Integer doctorId;
    private String name;
    private List<Slot> slots;
    private Speciality speciality;

    public Doctor(Integer doctorId, String name) {
        this.doctorId = doctorId;
        this.name = name;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Doctor{" +
            "doctorId=" + doctorId +
            ", name='" + name + '\'' +
            ", slots=" + slots +
            ", speciality=" + speciality +
            '}';
    }
}
