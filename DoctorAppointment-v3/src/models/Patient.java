package models;

public class Patient {
    private final Integer patientId;
    private String name;

    public Patient(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
