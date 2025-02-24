package services;

import models.Patient;
import repo.PatientRepo;

import java.util.concurrent.atomic.AtomicInteger;

public class PatientService {
    private final AtomicInteger patientIdGenerator;
    private final PatientRepo patientRepo;

    public PatientService(AtomicInteger patientIdGenerator, PatientRepo patientRepo) {
        this.patientIdGenerator = patientIdGenerator;
        this.patientRepo = patientRepo;
    }

    public Patient registerPatient(String name){
        validatePatientRegistrationReq(name);
        Patient patient = new Patient(patientIdGenerator.getAndIncrement());
        patient.setName(name);
        patientRepo.save(patient);
        System.out.printf("Patient: %s is registered successfully", patient.getName());
        return patient;
    }

    private void validatePatientRegistrationReq(String name) {
        if(name == null || name.isEmpty()){
            throw new RuntimeException("Name is required and should not be empty.");
        }
        if(patientRepo.findPatientByName(name).isPresent()){
            throw new RuntimeException("Patient with same name already exists.");
        }
    }

    public Patient findPatientById(Integer patientId) {
        return patientRepo.findPatientById(patientId).orElseThrow();
    }
}
