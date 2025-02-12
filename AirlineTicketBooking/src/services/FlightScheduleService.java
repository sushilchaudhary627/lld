package services;

import constants.FlightSeatStatus;
import models.*;
import repository.FlightRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class FlightScheduleService {
    private final FlightRepository flightRepository;
    private final AircraftService aircraftService;
    private final AtomicInteger flightIdGenerator;
    private final AtomicInteger seatIdGenerator;


    public FlightScheduleService(FlightRepository flightRepository, AircraftService aircraftService, AtomicInteger flightIdGenerator, AtomicInteger seatIdGenerator) {
        this.flightRepository = flightRepository;
        this.aircraftService = aircraftService;
        this.flightIdGenerator = flightIdGenerator;
        this.seatIdGenerator = seatIdGenerator;
    }

    public Flight createFlight(String name, LocalDateTime departureDate, int durationInMin, City from, City to, Double price) {
        Aircraft aircraft = aircraftService.getAircraftByName(name);
        validateFlightConflicts(aircraft.getAircraftId(), departureDate, durationInMin, from, to);
        List<FlightSeat> flightSeatList = new ArrayList<>();
        for (Seat seat : aircraft.getSeats()) {
            flightSeatList.add(new FlightSeat(seatIdGenerator.getAndIncrement(), seat, price, FlightSeatStatus.AVAILABLE));
        }
        Flight flight = new Flight(flightIdGenerator.getAndIncrement(), flightSeatList, from, to, departureDate, durationInMin);
        flight.setAircraft(aircraft);
        flightRepository.save(flight);
        return flight;
    }


    private void validateFlightConflicts(Integer aircraftId, LocalDateTime departureDate, int durationInMin, City from, City to) {
        // Check previous flight conflict.
        Optional<Flight> flightBefore = flightRepository.findLatestFlightBefore(aircraftId, departureDate);
        if (flightBefore.isPresent() &&
                flightBefore.get().getDepartureAt().plusMinutes(2L * flightBefore.get().getDurationInMin()).isAfter(departureDate)) {
            throw new IllegalArgumentException("Invalid departure date: insufficient gap after previous flight.");
        }

// Check next flight conflict.
        Optional<Flight> flightAfter = flightRepository.findEarliestFlightAfter(aircraftId, departureDate);
        if (flightAfter.isPresent()) {
            if (departureDate.plusMinutes(2L * durationInMin).isAfter(flightAfter.get().getDepartureAt())) {
                throw new IllegalArgumentException("Invalid departure date: insufficient gap before next flight.");
            }
        }


    }
}
// closest schedule flight before departureDate
//
