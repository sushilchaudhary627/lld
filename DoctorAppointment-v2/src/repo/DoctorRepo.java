package repo;
import constants.Speciality;
import models.*;

import java.time.LocalDateTime;
import java.util.*;
public interface DoctorRepo {
    public void save(Doctor doctor);
    public void update(Doctor doctor);
    public  Optional<Doctor>getDoctorByName(String name);
    public Optional<Doctor>getDoctorById(Integer doctorId);
    public List<Doctor> getDoctorsBySpeciality(Speciality speciality, LocalDateTime time);

}
