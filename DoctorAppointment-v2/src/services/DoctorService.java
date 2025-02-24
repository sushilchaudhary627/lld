package services;

import models.Doctor;
import models.Slot;
import repo.DoctorRepo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DoctorService {
    private final AtomicInteger doctorIdGenerator;
    private final DoctorRepo doctorRepo;
    private final SlotService slotService;

    public DoctorService(AtomicInteger doctorIdGenerator, DoctorRepo doctorRepo, SlotService slotService) {
        this.doctorIdGenerator = doctorIdGenerator;
        this.doctorRepo = doctorRepo;
        this.slotService = slotService;
    }

    public Doctor registerDoctor(String name){
        validateDoctorRegisterReq(name);
        Doctor doctor = new Doctor(doctorIdGenerator.getAndIncrement(), name);
        doctorRepo.save(doctor);
        System.out.printf("Doctor: %s is registered successfully\n", doctor.getName());
        return doctor;
    }

    private void validateDoctorRegisterReq(String name) {
        if(name == null || name.isEmpty()){
            throw new RuntimeException("Doctor name is required and should not be empty");
        }
        if(doctorRepo.getDoctorByName(name).isPresent()){
            throw new RuntimeException("Doctor with same name already exists");
        }
    }

    public Doctor markDoctorAvailability(Integer doctorId, List<LocalTime> slotStartTimes){
        Doctor doctor = doctorRepo.getDoctorById(doctorId).orElseThrow();
        List<Slot>slots = slotService.createSlots(slotStartTimes, doctor, LocalDate.now());
        doctor.setSlots(slots);
        return doctor;
    }

    public Doctor getDoctorById(Integer doctorId) {
        return doctorRepo.getDoctorById(doctorId).orElseThrow();
    }
}
