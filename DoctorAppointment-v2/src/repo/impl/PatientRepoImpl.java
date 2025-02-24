package repo.impl;

import models.Patient;
import repo.PatientRepo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PatientRepoImpl implements PatientRepo {
    Map<Integer, Patient> patientsById = new HashMap<>();
    Map<String, Patient>patientsByName = new HashMap<>();
    @Override
    public void save(Patient patient) {
        patientsById.put(patient.getPatientId(), patient);
        patientsByName.put(patient.getName(), patient);
    }

    @Override
    public void update(Patient patient) {

    }

    @Override
    public Optional<Patient> getPatientByName(String name) {
        return Optional.ofNullable(patientsByName.get(name));
    }

    @Override
    public Optional<Patient> getPatientById(Integer id) {
        return Optional.ofNullable(patientsById.get(id));
    }
}
