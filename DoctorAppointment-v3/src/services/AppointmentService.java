package services;

import constants.AppointmentStatus;
import models.*;
import models.Doctor;
import repo.AppointmentRepo;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AppointmentService {
    private final AtomicInteger appointmentIdGenerator;
    private final AppointmentRepo appointmentRepo;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final SlotService slotService;

    public AppointmentService(AtomicInteger appointmentIdGenerator, AppointmentRepo appointmentRepo, PatientService patientService, DoctorService doctorService, SlotService slotService) {
        this.appointmentIdGenerator = appointmentIdGenerator;
        this.appointmentRepo = appointmentRepo;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.slotService = slotService;
    }

    public Appointment createAppointment(Integer doctorId, Integer slotId, Integer patientId){
        Doctor doctor = doctorService.findDoctorById(doctorId);
        Patient patient = patientService.findPatientById(patientId);
        Slot slot =doctor.getSlots().stream().filter(s ->s.getSlotId().equals(slotId)).findFirst().orElseThrow();
        if(!slot.isAvailable()){
            throw new RuntimeException("Slot is already booked");
        }
        checkNonOverlappingAppointment(patient, slot);
        Appointment appointment = new Appointment(appointmentIdGenerator.getAndIncrement(), doctor,patient, AppointmentStatus.CONFIRMED, slot);
        appointmentRepo.save(appointment);
        slotService.updateSlotIsBooked(slot);
        return appointment;
    }

    public Appointment cancelAppointment(Integer appointmentId){
        Appointment appointment = appointmentRepo.findAppointmentById(appointmentId).orElseThrow();
        appointment.setAppointmentStatus(AppointmentStatus.CANCELED_BY_PATIENT);
        appointmentRepo.update(appointment);
        slotService.updateSlotIsAvailable(appointment.getSlot());
        return  appointment;
    }

    public List<Appointment> findAppointmentForPatient(Integer patientId){
        return appointmentRepo.findAppointmentForPatient(patientId);
    }

    public List<Appointment>findAppointmentForDoctor(Integer doctorId){
        return appointmentRepo.findAppointmentForDoctor(doctorId);
    }

    public void checkNonOverlappingAppointment(Patient patient, Slot slot){
        List<Appointment>appointments = findAppointmentForPatient(patient.getPatientId()).stream()
            .filter(a -> a.getAppointmentStatus() != AppointmentStatus.CANCELED_BY_PATIENT)
            .toList();
        for( Appointment appointment:appointments){
            System.out.println(slot);
            System.out.println(appointment.getSlot());
            if(slot.isOverLapping(appointment.getSlot())){
                throw new RuntimeException(String.format("Your appointment is overlapping with doctor %s\n", appointment.getDoctor().getName()));
            }
        }

    }
}
