package models;

import constants.AppointmentStatus;

import java.time.LocalDateTime;

public class Appointment {
    private final Integer appointmentId;
    private final LocalDateTime created;
    private Doctor doctor;
    private Patient patient;
    private Slot slot;
    private AppointmentStatus appointmentStatus;

    public Appointment(Integer appointmentId, LocalDateTime created) {
        this.appointmentId = appointmentId;
        this.created = created;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public LocalDateTime getCreated() {
        return created;
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

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }
}
