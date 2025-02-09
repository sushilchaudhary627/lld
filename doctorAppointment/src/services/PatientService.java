package services;

import models.Patient;
import repository.PatientRepository;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class PatientService {
    private final PatientRepository patientRepository;
    private final AtomicInteger patientIdGenerator;

    public PatientService(PatientRepository patientRepository, AtomicInteger patientIdGenerator) {
        this.patientRepository = patientRepository;
        this.patientIdGenerator = patientIdGenerator;
    }

    public Patient createPatient(String name){
        Objects.requireNonNull(name, "Patient name is required.");
        if(name.isEmpty()){
            throw new IllegalArgumentException("Name should not be empty");
        }
        Patient patient = new Patient(patientIdGenerator.getAndIncrement(), name);
        patientRepository.save(patient);
        return  patient;
    }

    public Patient getPatientById(Integer patientId){
        return patientRepository.getPatientById(patientId).orElseThrow(() -> new IllegalArgumentException("Patient doesn't exist"));
    }
}
