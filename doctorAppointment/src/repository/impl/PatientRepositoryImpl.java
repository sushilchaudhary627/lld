package repository.impl;

import models.Patient;
import repository.PatientRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PatientRepositoryImpl implements PatientRepository {
    Map<Integer, Patient>patientMap = new HashMap<>();
    @Override
    public void save(Patient patient) {
        patientMap.put(patient.getPatientId(), patient);
    }

    @Override
    public Optional<Patient> getPatientById(Integer id) {
        return Optional.ofNullable(patientMap.get(id));
    }
}
