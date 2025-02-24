package repo.impl;

import models.Patient;
import repo.PatientRepo;

import java.util.*;

public class PatientRepoImpl implements PatientRepo {
    Map<String, Patient>patientByNameMap = new HashMap<>();
    Map<Integer, Patient>patientByIdMap = new HashMap<>();
    @Override
    public void save(Patient patient) {
        patientByNameMap.put(patient.getName(),patient);
        patientByIdMap.put(patient.getPatientId(),  patient);
    }

    @Override
    public void update(Patient patient) {
        save(patient);
    }

    @Override
    public Optional<Patient> findPatientById(Integer id) {
        return Optional.ofNullable(patientByIdMap.get(id));
    }

    @Override
    public Optional<Patient> findPatientByName(String name) {
        return Optional.ofNullable(patientByNameMap.get(name));
    }
}
