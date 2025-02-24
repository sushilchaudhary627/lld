package models;

import constanta.AppointmentStatus;

import java.time.LocalDateTime;

public class Appointment {
    private final Long id;
    private Doctor doctor;
    private Patient patient;
    private AppointmentStatus appointmentStatus;
    private final LocalDateTime created;
    private Slot slot;
    public Appointment(Long id) {
        this.id = id;
        this.created = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }
}
