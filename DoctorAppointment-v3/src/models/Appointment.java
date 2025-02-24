package models;

import constants.AppointmentStatus;

public class Appointment {
    private final Integer appointmentId;
    private Doctor doctor;
    private Patient patient;
    private AppointmentStatus appointmentStatus;
    private Slot slot;

    public Appointment(Integer appointmentId, Doctor doctor, Patient patient, AppointmentStatus appointmentStatus, Slot slot) {
        this.appointmentId = appointmentId;
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentStatus = appointmentStatus;
        this.slot = slot;
    }

    public Integer getAppointmentId() {
        return appointmentId;
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

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    @Override
    public String toString() {
        return "\nAppointment{" +
            "appointmentId=" + appointmentId +
            ", doctor=" + doctor.getName() +
            ", patient=" + patient.getName() +
            ", appointmentStatus=" + appointmentStatus +
            ", slot=" + slot +
            '}';
    }
}
