package models;

public class Patient {
    private final  Integer patientId;
    private String name;


    public Patient(Integer id, String name) {
        this.patientId = id;
        this.name = name;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }
}
