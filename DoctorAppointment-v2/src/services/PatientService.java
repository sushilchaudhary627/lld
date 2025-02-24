package services;

import models.Appointment;
import models.Patient;
import repo.PatientRepo;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PatientService {
    private final AtomicInteger patientIdGenerator;
    private final PatientRepo patientRepo;

    public PatientService(AtomicInteger patientIdGenerator, PatientRepo patientRepo) {
        this.patientIdGenerator = patientIdGenerator;
        this.patientRepo = patientRepo;
    }

    public Patient registerPatient(String name){
        validatePatientRegisterReq(name);
        Patient patient = new Patient(patientIdGenerator.getAndIncrement(), name);
        patientRepo.save(patient);
        System.out.printf("Patient: %s is registered successfully.\n", patient.getName());
        return patient;
    }

    private void validatePatientRegisterReq(String name) {
        if(name == null || name.isBlank()){
            throw new RuntimeException("Name is required and should not be empty.");
        }

        if(patientRepo.getPatientByName(name).isPresent()){
            throw new RuntimeException("Patient with same name already exists.");
        }
    }

    public Patient getPatientById(Integer patientId) {
        return patientRepo.getPatientById(patientId).orElseThrow();
    }

}
