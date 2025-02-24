package models;

public class Patient {
    private final Integer patientId;
    private  String name;

    public Patient(Integer patientId, String name) {
        this.patientId = patientId;
        this.name = name;
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
