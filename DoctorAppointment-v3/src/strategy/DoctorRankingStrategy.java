package strategy;

import models.Doctor;

import java.util.List;

public interface DoctorRankingStrategy {
    public List<Doctor> rankDoctors(List<Doctor>doctors);
}
