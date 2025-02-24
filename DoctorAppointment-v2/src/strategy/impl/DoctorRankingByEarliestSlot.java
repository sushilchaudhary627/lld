package strategy.impl;

import models.Doctor;
import models.Slot;
import strategy.DoctorRankingStrategy;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class DoctorRankingByEarliestSlot implements DoctorRankingStrategy {
    @Override
    public List<Doctor> rank(List<Doctor> doctors) {
        return doctors.stream().sorted(Comparator.comparing(this::getEarliestDoctorAvailability)).toList();
    }

    private LocalDateTime getEarliestDoctorAvailability(Doctor doctor){
        return doctor.getSlots().stream()
            .filter(Slot::isAvailable)
            .map(Slot::getStartAt)
            .min(LocalDateTime::compareTo)
            .orElse(LocalDateTime.MAX);
    }
}
