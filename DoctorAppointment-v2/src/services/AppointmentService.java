package services;

import constants.AppointmentStatus;
import models.Appointment;
import models.Doctor;
import models.Patient;
import models.Slot;
import repo.AppointmentRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AppointmentService {
    private final AtomicInteger appointmentIdGenerator;
    private final AppointmentRepo appointmentRepo;
    private final SlotService slotService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public AppointmentService(AtomicInteger appointmentIdGenerator, AppointmentRepo appointmentRepo, SlotService slotService, DoctorService doctorService, PatientService patientService) {
        this.appointmentIdGenerator = appointmentIdGenerator;
        this.appointmentRepo = appointmentRepo;
        this.slotService = slotService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    public Appointment createAppointment(Integer doctorId, Integer patientId, Integer slotId){
        Doctor doctor = doctorService.getDoctorById(doctorId);
        Patient patient = patientService.getPatientById(patientId);
        Slot slot = doctor.getSlots().stream().filter(s -> s.getSlotId().equals(slotId)).findFirst().orElseThrow();
        if(!slot.isAvailable()){
            throw new RuntimeException("Slot is already booked.");
        }
        Appointment appointment = new Appointment(appointmentIdGenerator.getAndIncrement(), LocalDateTime.now());
        appointment.setSlot(slot);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentStatus(AppointmentStatus.CONFIRMED);
        slotService.setSlotIsBooked(slot);
        appointmentRepo.save(appointment);
        return appointment;
    }

    public Appointment cancelAppointment(Integer appointmentId){
        Appointment appointment = appointmentRepo.findAppointmentById(appointmentId).orElseThrow();
        slotService.setSlotIsAvailable(appointment.getSlot());
        appointment.setAppointmentStatus(AppointmentStatus.CANCELLED_BY_PATIENT);
        appointmentRepo.update(appointment);
        return appointment;
    }

    public List<Appointment> findAppointmentsForDoctor(Integer doctorId){
        return appointmentRepo.getAppointmentsForDoctor(doctorId);
    }

    public List<Appointment>findAppointmentForPatient(Integer patientId){
        return appointmentRepo.getAppointmentForPatient(patientId);
    }
}
