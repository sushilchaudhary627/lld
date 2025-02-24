package strategy.impl;

import constanta.SlotStatus;
import models.Doctor;
import models.Slot;
import strategy.DoctorRankingStrategy;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class DoctorRankingByEarlierSlot implements DoctorRankingStrategy {
    @Override
    public List<Doctor> rankDoctors(List<Doctor> doctors) {
        return  doctors.stream().sorted(Comparator.comparing(this::getEarlierAvailablityOfDoctor, Comparator.naturalOrder())).toList();
    }

    public LocalDateTime getEarlierAvailablityOfDoctor(Doctor doctor){
        return doctor.getSlots().stream()
            .filter(Slot::isAvailable)
            .map(Slot::getSlotStart)
            .min(LocalDateTime::compareTo)
            .orElse(LocalDateTime.MAX);
    }
}
