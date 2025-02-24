package repo;
import java.util.*;
import models.*;
public interface PatientRepo {
    public void save(Patient patient);
    public void update(Patient patient);
    public Optional<Patient>findPatientById(Integer id);
    public Optional<Patient>findPatientByName(String name);
}
