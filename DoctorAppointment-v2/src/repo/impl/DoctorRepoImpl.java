package repo.impl;

import constants.Speciality;
import models.Doctor;
import repo.DoctorRepo;

import java.time.LocalDateTime;
import java.util.*;

public class DoctorRepoImpl implements DoctorRepo {
    Map<Integer, Doctor> doctorsById = new HashMap<>();
    Map<String , Doctor>doctorsByName = new HashMap<>();
    Map<Speciality, List<Doctor>> doctorsBySpeciality = new HashMap<>();
    @Override
    public void save(Doctor doctor) {
        doctorsById.put(doctor.getDoctorId(), doctor);
        doctorsByName.put(doctor.getName(), doctor);
        doctorsBySpeciality.computeIfAbsent(doctor.getSpeciality(), k-> new ArrayList<>()).add(doctor);
    }

    @Override
    public void update(Doctor doctor) {

    }

    @Override
    public Optional<Doctor> getDoctorByName(String name) {
        return Optional.ofNullable(doctorsByName.get(name));
    }

    @Override
    public Optional<Doctor> getDoctorById(Integer doctorId) {
        return Optional.ofNullable(doctorsById.get(doctorId));
    }

    @Override
    public List<Doctor> getDoctorsBySpeciality(Speciality speciality, LocalDateTime time) {
        return doctorsBySpeciality.getOrDefault(speciality, new ArrayList<>()).stream()
            .filter( doctor -> doctor.getSlots().stream().anyMatch( s -> s.getStartAt().isAfter(time)))
            .toList();
    }

}
