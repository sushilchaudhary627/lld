package services;

import models.Flight;
import repository.BookingRepository;
import repository.FlightRepository;

import java.time.LocalDate;
import java.util.List;

public class RevenueService {
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;

    public RevenueService(BookingRepository bookingRepository, FlightRepository flightRepository) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
    }

    /**
     * Computes the total revenue for all completed flights of a specific aircraft on a given date.
     *
     * @param aircraftName the name of the aircraft
     * @param date         the date on which revenue is computed
     * @return the total revenue as a Double
     */
    public Double getRevenue(String aircraftName, LocalDate date) {
        List<Flight> flights = flightRepository.getCompletedFlights(aircraftName, date);
        return flights.stream()
                .mapToDouble(flight -> bookingRepository.getCompletedBookingForFlight(flight.getFlightId()))
                .sum();
    }
}
