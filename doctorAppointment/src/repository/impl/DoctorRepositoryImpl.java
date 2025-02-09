package repository.impl;

import models.Doctor;
import models.ScheduleStatus;
import models.Slot;
import models.Speciality;
import repository.DoctorRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class DoctorRepositoryImpl implements DoctorRepository {
    Map<Integer, Doctor>doctorMap = new HashMap<>();
    @Override
    public void save(Doctor doctor) {
      doctorMap.put(doctor.getDoctorId(), doctor);
    }

    @Override
    public Optional<Doctor> getDoctorById(Integer doctorId) {
        return Optional.ofNullable(doctorMap.get(doctorId));
    }

    @Override
    public List<Doctor> getDoctorBySpeciality(Speciality speciality) {
        return doctorMap.values().stream()
                .filter(doctor ->
                        Objects.equals(speciality, doctor.getSpeciality()) &&
                                doctor.getSlotsForToday().stream()
                                        .anyMatch(slot ->
                                                slot.getStartAt().isAfter(LocalDateTime.now()) &&
                                                        Objects.equals(ScheduleStatus.AVAILABLE, slot.getScheduleStatus())
                                        )
                )
                .collect(Collectors.toList());  // Fixed Collector
    }

    @Override
    public void update(Doctor doctor) {

    }
}
