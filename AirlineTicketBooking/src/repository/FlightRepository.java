package repository;

import models.Flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightRepository {
    void save(Flight flight);

    List<Flight> findScheduledFlights(Integer aircraftId, LocalDateTime startTime, LocalDateTime endTime);

    Optional<Flight> findLatestFlightBefore(Integer aircraftId, LocalDateTime referenceTime);

    Optional<Flight> findEarliestFlightAfter(Integer aircraftId, LocalDateTime referenceTime);

    Optional<Flight> getFightById(Integer flightId);

    List<Flight> getCompletedFlights(String aircraftName, LocalDate date);
}
