package services;

import models.*;
import repository.BookingRepository;
import repository.FlightRepository;
import util.PNRGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ReservationService {
    private final FlightSeatService flightSeatService;
    private final UserService userService;
    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;
    private final AtomicInteger bookingIdGenerator;
    public ReservationService(FlightSeatService flightSeatService, UserService userService, FlightRepository flightRepository, BookingRepository bookingRepository, AtomicInteger bookingIdGenerator) {
        this.flightSeatService = flightSeatService;
        this.userService = userService;
        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
        this.bookingIdGenerator = bookingIdGenerator;
    }

    public Booking createReservation(Integer userId, Integer flightId, List<Integer> selectedSeatIds){
        User user = userService.getUserByUserId(userId);
        Flight flight = flightRepository.getFightById(flightId).orElseThrow();
        List<FlightSeat>selectedSeats =  new ArrayList<>();
        for(FlightSeat flightSeat:flight.getFlightSeats()){
            if(selectedSeatIds.stream().anyMatch( k -> flightSeat.getFlightSeatId().equals(k))){
                selectedSeats.add(flightSeat);
            }
        }
        flightSeatService.checkAvailabilityAndMarkUnAvailable(selectedSeats);
        try {
            Booking booking = new Booking(bookingIdGenerator.getAndIncrement());
            booking.setFlight(flight);
            for (FlightSeat seat : selectedSeats) {
                booking.addFlightSeatItem(new FlightSeatItem(seat, seat.getPrice()));
            }
            booking.setBookedBy(user);
            booking.setPnr(getUniquePNR());
            bookingRepository.save(booking);
            return booking;
        } catch(Exception e){
            flightSeatService.MarkAvailable(selectedSeats);
            throw new RuntimeException(e.getMessage());
        }
    }


    private String getUniquePNR(){
        String pnr = PNRGenerator.generate();
        int retryCount = 10;
        while(bookingRepository.getBookingByPnr(pnr).isPresent()){
            if(retryCount == 0){
                throw new RuntimeException("Failed to generate PNR number");
            }
            pnr = PNRGenerator.generate();
            retryCount--;
        }
        return pnr;
    }
}
