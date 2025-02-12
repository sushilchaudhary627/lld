package repository.impl;

import models.Aircraft;
import models.Flight;
import repository.FlightRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class FlightRepositoryImpl implements FlightRepository {
    Map<Integer, List<Flight>> flightByAircraft = new HashMap<>();
    Map<Integer, Flight>flightMap = new HashMap<>();
    @Override
    public void save(Flight flight) {
        flightMap.put(flight.getFlightId(), flight);
        flightByAircraft.putIfAbsent(flight.getAircraft().getAircraftId(), new ArrayList<>());
        flightByAircraft.get(flight.getAircraft().getAircraftId()).add(flight);
    }

    @Override
    public List<Flight> findScheduledFlights(Integer aircraftId, LocalDateTime startTime, LocalDateTime endTime) {
        return List.of();
    }

    @Override
    public Optional<Flight> findLatestFlightBefore(Integer aircraftId, LocalDateTime referenceTime) {
        List<Flight> flights = flightByAircraft.get(aircraftId);
        if (flights == null) {
            return Optional.empty();
        }
        // Filter flights with departure time strictly before the reference time,
        // then find the one with the maximum departure time.
        return flights.stream()
                .filter(flight -> flight.getDepartureAt().isBefore(referenceTime))
                .max(Comparator.comparing(Flight::getDepartureAt));
    }

    @Override
    public Optional<Flight> findEarliestFlightAfter(Integer aircraftId, LocalDateTime referenceTime) {
        List<Flight> flights = flightByAircraft.get(aircraftId);
        if (flights == null) {
            return Optional.empty();
        }
        // Filter flights with departure time strictly after the reference time,
        // then find the one with the minimum departure time.
        return flights.stream()
                .filter(flight -> flight.getDepartureAt().isAfter(referenceTime))
                .min(Comparator.comparing(Flight::getDepartureAt));
    }

    @Override
    public Optional<Flight> getFightById(Integer flightId) {
        return Optional.ofNullable(flightMap.get(flightId));
    }

    @Override
    public List<Flight> getCompletedFlights(String aircraftName, LocalDate date) {
        return List.of();
    }
}
