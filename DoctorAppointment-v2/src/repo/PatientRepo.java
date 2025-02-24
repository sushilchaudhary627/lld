package repo;
import models.*;
import java.util.*;
public interface PatientRepo {
    void save(Patient patient);
    void update(Patient patient);
    public Optional<Patient>getPatientByName(String name);
    public Optional<Patient>getPatientById(Integer id);
}
