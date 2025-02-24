package services;

import constanta.Speciality;
import models.Doctor;
import models.Slot;
import repo.DoctorRepo;
import strategy.DoctorRankingStrategy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class DoctorService {
    private final AtomicLong doctorIdGen;
    private final DoctorRepo doctorRepo;
    private DoctorRankingStrategy doctorRankingStrategy;
    private final SlotService slotService;

    public DoctorService(AtomicLong doctorIdGen, DoctorRepo doctorRepo, DoctorRankingStrategy doctorRankingStrategy, SlotService slotService) {
        this.doctorIdGen = doctorIdGen;
        this.doctorRepo = doctorRepo;
        this.doctorRankingStrategy = doctorRankingStrategy;
        this.slotService = slotService;
    }

    public Doctor registerDoctor(String name, Speciality speciality){
        validateDoctorRegistrationReq(name);
        Doctor doctor = new Doctor(doctorIdGen.getAndIncrement());
        doctor.setName(name);
        doctor.setSpeciality(speciality);
        doctorRepo.save(doctor);
        System.out.printf("Doctor: %s is registered successfully.\n", doctor.getName());
        return doctor;
    }

    private void validateDoctorRegistrationReq(String name) {
        Objects.requireNonNull(name, "Name is required.");
        if(name.isEmpty()){
            throw new RuntimeException("Doctor name should not be empty.");
        }
        if(doctorRepo.findDoctorByName(name).isPresent()){
            throw new RuntimeException("Doctor with same name already exist.");
        }
    }

    public Doctor markAvailableSlots(List<LocalTime> slotStartingTimes, Long doctorId){
        Doctor doctor = doctorRepo.findDoctorById(doctorId).orElseThrow();
        List<Slot> slots = slotService.createSlots(slotStartingTimes, LocalDate.now());
        doctor.setSlots(slots);
        doctorRepo.update(doctor);
        return  doctor;
    }

    public List<Doctor> findAvailableSlots(Speciality speciality){
        List<Doctor>doctors = doctorRepo.findDoctorsBySpeciality(speciality, LocalDateTime.now());
        return doctorRankingStrategy.rankDoctors(doctors);
    }

    public Doctor findDoctorById(Long doctorId) {
        return doctorRepo.findDoctorById(doctorId).orElseThrow();
    }
}
