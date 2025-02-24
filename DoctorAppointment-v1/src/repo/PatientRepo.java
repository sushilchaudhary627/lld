package repo;
import models.*;
import java.util.*;
public interface PatientRepo {
    public void save(Patient patient);
    public Optional<Patient>findPatientByName(String name);
    public  Optional<Patient>findPatientById(Long id);
}
