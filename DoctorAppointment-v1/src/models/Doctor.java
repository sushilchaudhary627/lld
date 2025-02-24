package models;

import constanta.Speciality;

import java.util.List;

public class Doctor {
    private final Long id;
    private String name;
    private List<Slot> slots;
    private Speciality speciality;

    public Doctor(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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
        return "\nDoctor{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", slots=" + slots +
            ", speciality=" + speciality +
            '}';
    }
}
