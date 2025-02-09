package repository;
import  java.util.*;
import models.Doctor;
import models.Speciality;

import java.util.Optional;

public interface DoctorRepository {
    public  void  save(Doctor doctor);
    public Optional<Doctor> getDoctorById(Integer doctorId);
    public  List<Doctor> getDoctorBySpeciality(Speciality speciality);

    void update(Doctor doctor);
}
