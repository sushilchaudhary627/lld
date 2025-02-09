package services;

import models.Doctor;
import models.ScheduleStatus;
import models.Slot;
import models.Speciality;
import repository.DoctorRepository;
import strategy.DoctorRankingStrategy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class DoctorService {
    private static final int SLOT_DURATION_MINUTES = 30;
    private final DoctorRepository doctorRepository;
    private final AtomicInteger doctorIdGenerator;
    private final AtomicInteger slotIdGenerator;

    public DoctorService(DoctorRepository doctorRepository, AtomicInteger doctorIdGenerator, AtomicInteger slotIdGenerator) {
        this.doctorRepository = doctorRepository;
        this.doctorIdGenerator = doctorIdGenerator;
        this.slotIdGenerator = slotIdGenerator;
    }

    public Doctor createDoctor(String name, String speciality) {
        Objects.requireNonNull(name, "Doctor name is required.");
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Doctor name cannot be empty.");
        }
        Speciality doctorSpeciality = Speciality.fromString(speciality);
        Integer id = doctorIdGenerator.getAndIncrement();
        Doctor doctor = new Doctor(id, name, doctorSpeciality, LocalDateTime.now());
        doctorRepository.save(doctor);
        return doctor;
    }

    public Doctor markAvailability(Integer doctorId, LocalDate date, List<LocalTime> slots) {
        Doctor doctor = doctorRepository.getDoctorById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor with ID " + doctorId + " not found."));

        for (LocalTime startTime : slots) {
            LocalDateTime slotStart = date.atTime(startTime);
            LocalDateTime slotEnd = slotStart.plusMinutes(SLOT_DURATION_MINUTES);

            // Check if slot already exists to prevent duplicates
            if (doctor.hasOverlappingSlot(slotStart, slotEnd)) {
                System.out.println("Skipping overlapping slot: " + slotStart);
                continue;
            }

            int slotId = slotIdGenerator.getAndIncrement();
            Slot slot = new Slot(slotId, doctor);
            slot.setStartAt(slotStart);
            slot.setEndAt(slotEnd);
            slot.setScheduleStatus(ScheduleStatus.AVAILABLE);
            doctor.addSlot(slot);
        }

        doctorRepository.update(doctor);
        return doctor;
    }

    public List<Doctor> getAvailableDoctorHavingSpeciality(String speciality, DoctorRankingStrategy doctorRankingStrategy) {
        return doctorRankingStrategy.
                rankDoctors(doctorRepository.getDoctorBySpeciality(
                        Speciality.fromString(speciality)));

    }
    public Doctor getDoctorById(Integer doctorId){
        return doctorRepository.getDoctorById(doctorId).orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
    }


}
