package repository;

import models.Patient;

import java.util.Optional;

public interface PatientRepository {
    public void save(Patient patient);
    public Optional<Patient> getPatientById(Integer id);
}
