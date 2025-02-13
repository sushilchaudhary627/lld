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
        Runnable bookingTask = () -> {
            try {
                Booking booking = reservationService.createReservation(
                        admin.getUserId(),
                        flight.getFlightId(),
                        List.of(1)   // Booking seat with id 1
                );
                System.out.println(Thread.currentThread().getName() + " Booking successful: " + booking);
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + " Booking failed: " + e.getMessage());
            }
        };

        Thread thread1 = new Thread(bookingTask, "Thread-1");
        Thread thread2 = new Thread(bookingTask, "Thread-2");

        // Start both threads.
        thread1.start();
        thread2.start();

        // Wait for both threads to complete.
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}