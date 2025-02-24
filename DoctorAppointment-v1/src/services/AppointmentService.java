package services;

import constanta.AppointmentStatus;
import constanta.SlotStatus;
import models.Appointment;
import models.Doctor;
import models.Patient;
import models.Slot;
import repo.AppointmentRepo;

import java.util.concurrent.atomic.AtomicLong;

public class AppointmentService {
    private final AtomicLong appointmentIdGen ;
    private final AppointmentRepo appointmentRepo;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final SlotService slotService;


    public AppointmentService(AtomicLong appointmentIdGen, AppointmentRepo appointmentRepo, DoctorService doctorService, PatientService patientService, SlotService slotService) {
        this.appointmentIdGen = appointmentIdGen;
        this.appointmentRepo = appointmentRepo;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.slotService = slotService;
    }

    public Appointment createAppointment(Long doctorId, Long patientId, Long slotId){
        Doctor doctor = doctorService.findDoctorById(doctorId);
        Patient patient = patientService.findPatientById(patientId);
        Slot slot = doctor.getSlots().stream().filter(s ->s.getId().equals(slotId)).findFirst().orElseThrow();
        if(!slot.isAvailable()){
            throw new RuntimeException("Slot is not available.");
        }
        Appointment appointment = new Appointment(appointmentIdGen.getAndIncrement());
        slotService.markSlotBooked(slot);
        appointment.setAppointmentStatus(AppointmentStatus.CONFIRMED);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setSlot(slot);
        appointmentRepo.save(appointment);
        return appointment;
    }

    public Appointment cancelAppointment(Long id){
        Appointment appointment = appointmentRepo.findAppointmentById(id).orElseThrow();
        appointment.setAppointmentStatus(AppointmentStatus.CANCELLED_BY_PATIENT);
        slotService.markSlotAvailable(appointment.getSlot());
        return appointment;
    }
}
