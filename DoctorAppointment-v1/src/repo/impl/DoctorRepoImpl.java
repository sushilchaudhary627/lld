package repo.impl;

import constanta.Speciality;
import models.Doctor;
import repo.DoctorRepo;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class DoctorRepoImpl  implements DoctorRepo {
    Map<Long , Doctor>doctorsByIdMap = new HashMap<>();
    Map<String , Doctor>doctorsByNamemap = new HashMap<>();
    Map<Speciality, List<Doctor>>doctorsBySpecialityMap = new HashMap<>();
    @Override
    @Override
    public List<Doctor> findDoctorsBySpeciality(Speciality speciality, LocalDateTime time) {
        return doctorsBySpecialityMap.getOrDefault(speciality, List.of()).stream()
            .filter(doctor -> doctor.getSlots().stream()
                .anyMatch(slot -> slot.getSlotStart().isAfter(time))) // Corrected filtering logic
            .toList();
    }


    @Override
    public void save(Doctor doctor) {
       doctorsByIdMap.put(doctor.getId(), doctor);
       doctorsByNamemap.put(doctor.getName(), doctor);
       doctorsBySpecialityMap.computeIfAbsent(doctor.getSpeciality(),
           k -> new ArrayList<>()).add(doctor);
    }

    @Override
    public void update(Doctor doctor) {

    }

    @Override
    public Optional<Doctor> findDoctorById(Long id) {
        return Optional.ofNullable(doctorsByIdMap.get(id));
    }

    @Override
    public Optional<Doctor> findDoctorByName(String name) {
        return Optional.ofNullable(doctorsByNamemap.get(name));
    }
}
