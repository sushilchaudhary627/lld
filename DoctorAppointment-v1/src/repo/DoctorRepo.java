package repo;
import constanta.Speciality;
import models.*;

import java.time.LocalDateTime;
import java.util.*;

public interface DoctorRepo {
    public List<Doctor> findDoctorsBySpeciality(Speciality speciality, LocalDateTime time);
    public void save(Doctor doctor);
    public void update(Doctor doctor);
    public Optional<Doctor> findDoctorById(Long id);
    public Optional<Doctor>findDoctorByName(String name);
}
