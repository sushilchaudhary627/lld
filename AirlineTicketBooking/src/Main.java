import models.*;
import repository.AircraftRepository;
import repository.BookingRepository;
import repository.FlightRepository;
import repository.UserRepository;
import repository.impl.AircraftRepositoryImpl;
import repository.impl.FlightRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import repository.impl.BookingRepositoryImpl;
import services.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl();
        BookingRepository bookingRepository = new BookingRepositoryImpl();
        FlightRepository flightRepository = new FlightRepositoryImpl();
        AircraftRepository aircraftRepository = new AircraftRepositoryImpl();
        UserService userService = new UserService(userRepository);
        AircraftService aircraftService = new AircraftService(userService, aircraftRepository, new AtomicInteger());
        FlightScheduleService flightScheduleService = new FlightScheduleService(flightRepository, aircraftService, new AtomicInteger(), new AtomicInteger());
        ReservationService reservationService = new ReservationService(new FlightSeatService(), userService, flightRepository, bookingRepository, new AtomicInteger());
        User admin   = userService.createAdmin("Admin");
        System.out.println(admin);
        Aircraft aircraft = aircraftService.addAircraft(admin.getUserId(), "Indigo", 12);
        System.out.println(aircraft);
        Flight flight = flightScheduleService.createFlight(aircraft.getAircraftName(), LocalDateTime.now(), 10, new City(), new City(), 100.00);
        System.out.println(flight);
        Booking booking = reservationService.createReservation(admin.getUserId(), flight.getFlightId(), List.of(1));
        System.out.println(booking);
    }
}