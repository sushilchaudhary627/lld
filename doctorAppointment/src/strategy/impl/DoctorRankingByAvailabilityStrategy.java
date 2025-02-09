package strategy.impl;

import models.Doctor;
import models.ScheduleStatus;
import models.Slot;
import strategy.DoctorRankingStrategy;

import java.time.LocalDateTime;
import java.util.*;

public class DoctorRankingByAvailabilityStrategy implements DoctorRankingStrategy {
    @Override
    public List<Doctor> rankDoctors(List<Doctor> doctorList) {
        // Compute earliest available slot once for each doctor to improve efficiency
        Map<Doctor, LocalDateTime> earliestAvailableMap = new HashMap<>();

        for (Doctor doctor : doctorList) {
            earliestAvailableMap.put(doctor,
                    getEarliest(LocalDateTime.now(), doctor.getSlotsForToday()).orElse(LocalDateTime.MAX)
            );
        }

        // Sort based on the earliest available slot
        doctorList.sort(Comparator.comparing(earliestAvailableMap::get));

        return doctorList;
    }

    private Optional<LocalDateTime> getEarliest(LocalDateTime dateTime, List<Slot> slots) {
        return slots.stream()
                .filter(slot -> slot.getStartAt().isAfter(dateTime) && slot.getScheduleStatus() == ScheduleStatus.AVAILABLE)
                .map(Slot::getStartAt)
                .min(Comparator.naturalOrder()); // Get the earliest available slot
    }
}
