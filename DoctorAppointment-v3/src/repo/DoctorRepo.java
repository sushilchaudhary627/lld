package repo;
import java.time.LocalDateTime;
import java.util.*;

import constants.Speciality;
import models.*;
public interface DoctorRepo {
    public void save(Doctor doctor);
    public void update(Doctor doctor);
    public Optional<Doctor>findDoctorById(Integer doctorId);
    public List<Doctor> findAvailableDoctors(Speciality speciality, LocalDateTime time);
    public Optional<Doctor>findDoctorByName(String name);
}
