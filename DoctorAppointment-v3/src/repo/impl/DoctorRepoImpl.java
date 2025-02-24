package repo.impl;

import constants.Speciality;
import models.Doctor;
import repo.DoctorRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.*;

public class DoctorRepoImpl implements DoctorRepo {
    Map<Integer, Doctor>doctorByIdMap = new HashMap<>();
    Map<Speciality, List<Doctor>>doctorBySpecilaityMap = new HashMap<>();
    Map<String, Doctor>doctorByNameMap = new HashMap<>();
    @Override
    public void save(Doctor doctor) {
        doctorByIdMap.put(doctor.getDoctorId(), doctor);
        doctorByNameMap.put(doctor.getName(), doctor);
        doctorBySpecilaityMap.computeIfAbsent(doctor.getSpeciality(), k -> new ArrayList<>())
            .add(doctor);
    }

    @Override
    public void update(Doctor doctor) {
        save(doctor);
    }

    @Override
    public Optional<Doctor> findDoctorById(Integer doctorId) {
        return Optional.ofNullable(doctorByIdMap.get(doctorId));
    }

    @Override
    public List<Doctor> findAvailableDoctors(Speciality speciality, LocalDateTime time) {
        return doctorBySpecilaityMap.getOrDefault(speciality, List.of()).stream()
            .filter(doctor -> doctor.getSlots().stream().anyMatch(s -> s.isAvailable() && s.getStartAt().isAfter(time)))
            .toList();

    }

    @Override
    public Optional<Doctor> findDoctorByName(String name) {
        return Optional.ofNullable(doctorByNameMap.get(name));
    }
}
