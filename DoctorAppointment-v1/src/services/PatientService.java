package services;

import models.Patient;
import repo.PatientRepo;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class PatientService {
    private final AtomicLong patientIdGen;
    private final PatientRepo patientRepo;

    public PatientService(AtomicLong patientIdGen, PatientRepo patientRepo) {
        this.patientIdGen = patientIdGen;
        this.patientRepo = patientRepo;
    }

    public Patient regsiterPatient(String name){
        validatePatientRegisterReq(name);
        Patient patient = new Patient(patientIdGen.getAndIncrement());
        patient.setName(name);
        patientRepo.save(patient);
        System.out.printf("Patient: %s is registered successfully\n", patient.getName());
        return patient;
    }

    private void validatePatientRegisterReq(String name) {
        Objects.requireNonNull(name, "name is required.");
        if(name.isEmpty()){
            throw new RuntimeException("Name should not be empty.");
        }
        if(patientRepo.findPatientByName(name).isPresent()){
            throw new RuntimeException("Patient with same name is already present.");
        }
    }

    public Patient findPatientById(Long patientId) {
        return patientRepo.findPatientById(patientId).orElseThrow();
    }
}
