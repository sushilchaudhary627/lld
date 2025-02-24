package repo.impl;

import models.Patient;
import repo.PatientRepo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PatientRepoImpl implements PatientRepo {
    Map<Long, Patient>patientsByIdMap = new HashMap<>();
    Map<String, Patient> patientsByNameMap = new HashMap<>();
    @Override
    public void save(Patient patient) {
        patientsByIdMap.put(patient.getId(), patient);
        patientsByNameMap.put(patient.getName(), patient);
    }

    @Override
    public Optional<Patient> findPatientByName(String name) {
        return Optional.ofNullable(patientsByNameMap.get(name));
    }

    @Override
    public Optional<Patient> findPatientById(Long id) {
        return Optional.ofNullable(patientsByIdMap.get(id));
    }
}
