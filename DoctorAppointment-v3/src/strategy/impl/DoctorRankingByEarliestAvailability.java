package strategy.impl;

import models.Doctor;
import models.Slot;
import strategy.DoctorRankingStrategy;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class DoctorRankingByEarliestAvailability implements DoctorRankingStrategy {
    @Override
    public List<Doctor> rankDoctors(List<Doctor> doctors) {
        return doctors.stream().
            sorted(Comparator.comparing(this::getEarliestAvailabilityForDoctor))
            .toList();
    }

    private LocalDateTime getEarliestAvailabilityForDoctor(Doctor doctor){
        return doctor.getSlots().stream()
            .map(Slot::getStartAt)
            .min(Comparator.naturalOrder())
            .orElse(LocalDateTime.MAX);
    }
}
