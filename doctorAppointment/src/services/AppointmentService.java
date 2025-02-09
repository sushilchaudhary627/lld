package services;

import models.*;
import repository.AppointmentRepository;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final SlotService slotService;
    private final AtomicInteger appointmentIdGenerator;

    public AppointmentService(AppointmentRepository appointmentRepository, PatientService patientService, DoctorService doctorService, SlotService slotService, AtomicInteger appointmentIdGenerator) {
        this.appointmentRepository = appointmentRepository;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.slotService = slotService;
        this.appointmentIdGenerator = appointmentIdGenerator;
    }

    public Appointment bookAppointment(Integer patientId, Integer doctorId, Integer slotId) {
        Patient patient = patientService.getPatientById(patientId);
        Doctor doctor = doctorService.getDoctorById(doctorId);

        // Find slot in doctor's availability
        Slot slot = doctor.getSlotsForToday().stream()
                .filter(s -> Objects.equals(s.getScheduleId(), slotId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Slot ID " + slotId + " not found for Doctor ID " + doctorId));

        // Handle appointment booking
        AppointmentStatus status = (slot.getScheduleStatus() == ScheduleStatus.AVAILABLE)
                ? AppointmentStatus.CONFIRMED
                : AppointmentStatus.IN_QUEUE;

        if (status == AppointmentStatus.CONFIRMED) {
            slot.setScheduleStatus(ScheduleStatus.BOOKED);
        }

        Appointment appointment = new Appointment(appointmentIdGenerator.getAndIncrement(), patient, doctor, slot);
        appointment.setAppointmentStatus(status);
        appointmentRepository.save(appointment);

        return appointment;
    }

    public Appointment cancelAppointment(Integer appointmentId, Integer patientId) {
        Appointment appointment = appointmentRepository.getAppointmentById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment ID " + appointmentId + " not found."));

        // Ensure patient is canceling their own appointment
        if (!appointment.getPatient().getPatientId().equals(patientId)) {
            throw new IllegalArgumentException("Unauthorized: Patient ID " + patientId + " cannot cancel this appointment.");
        }

        // Cancel appointment
        appointment.setAppointmentStatus(AppointmentStatus.CANCELLED_BY_PATIENT);
        appointmentRepository.update(appointment);

        // Call slotService to free up the slot and promote a waitlisted patient
        slotService.slotIsCancelled(appointment.getDoctor(), appointment.getSlot());

        return appointment;
    }

    public Appointment confirmAppointment(Integer appointmentId) {
        Appointment appointment = appointmentRepository.getAppointmentById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with ID: " + appointmentId));
        if (appointment.getAppointmentStatus() != AppointmentStatus.IN_QUEUE) {
            throw new IllegalStateException("Only appointments in queue can be confirmed.");
        }
        appointment.setAppointmentStatus(AppointmentStatus.CONFIRMED);
        appointment.getSlot().setScheduleStatus(ScheduleStatus.BOOKED);
        appointmentRepository.update(appointment);
        return appointment;
    }

}
