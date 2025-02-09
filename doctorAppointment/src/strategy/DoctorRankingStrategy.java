package strategy;

import models.Doctor;

import java.util.List;

public interface DoctorRankingStrategy {
    List<Doctor> rankDoctors(List<Doctor> doctorList);
}
