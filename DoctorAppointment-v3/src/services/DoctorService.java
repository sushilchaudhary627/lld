package services;

import constants.Speciality;
import models.Doctor;
import models.Slot;
import repo.DoctorRepo;
import strategy.DoctorRankingStrategy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DoctorService {
    private final AtomicInteger doctorIdGenerator;
    private final DoctorRepo doctorRepo;
    private final SlotService slotService;
    private DoctorRankingStrategy doctorRankingStrategy;

    public DoctorService(AtomicInteger doctorIdGenerator, DoctorRepo doctorRepo, SlotService slotService, DoctorRankingStrategy doctorRankingStrategy) {
        this.doctorIdGenerator = doctorIdGenerator;
        this.doctorRepo = doctorRepo;
        this.slotService = slotService;
        this.doctorRankingStrategy = doctorRankingStrategy;
    }

    public Doctor registerDoctor(String name, Speciality speciality){
        validateDoctorRegistrationReq(name);
        Doctor doctor = new Doctor(doctorIdGenerator.getAndIncrement(), name);
        doctor.setSpeciality(speciality);
        doctorRepo.save(doctor);
        System.out.printf("Doctor: %s is registered successfully\n", doctor.getName());
        return doctor;

    }

    private void validateDoctorRegistrationReq(String name) {
        if(name == null || name.isEmpty()){
            throw new RuntimeException("Name is required and should not be empty.");
        }
        if(doctorRepo.findDoctorByName(name).isPresent()){
            throw new RuntimeException("Doctor with same name already exists.");
        }
    }


    public List<Doctor> findDoctorsBySpeciality(Speciality speciality){
        return doctorRankingStrategy.rankDoctors(
            doctorRepo.findAvailableDoctors(speciality, LocalDateTime.now()));
    }

    public Doctor markAvailability(Integer doctorId, List<LocalTime>slotStartTimes){
        Doctor doctor = doctorRepo.findDoctorById(doctorId).orElseThrow();
        List<Slot>slots = slotService.createSlots(doctor, slotStartTimes, LocalDate.now());
        doctor.setSlots(slots);
        System.out.printf("Doctor slots %s saved succesfully ", slots.size());
        return doctor;
    }

    public Doctor findDoctorById(Integer doctorId) {
        return doctorRepo.findDoctorById(doctorId).orElseThrow();
    }
}
