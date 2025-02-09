package models;

public class Appointment {
    private final Integer appointmentId;
    private Patient patient;
    private Doctor doctor;
    private Slot slot;

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    private AppointmentStatus appointmentStatus;

    public Appointment(Integer appointmentId, Patient patient, Doctor doctor, Slot slot) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.slot = slot;
        this.appointmentStatus = appointmentStatus;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Slot getSlot() {
        return slot;
    }
}
