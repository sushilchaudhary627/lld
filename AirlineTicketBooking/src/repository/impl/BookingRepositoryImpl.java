package repository.impl;

import models.Booking;
import repository.BookingRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

 public class BookingRepositoryImpl implements BookingRepository {
    Map<String, Booking> bookingMap = new HashMap<>();
    @Override
    public void save(Booking booking) {
        bookingMap.put(booking.getPnr(), booking);
    }

    @Override
    public Optional<Booking> getBookingByPnr(String pnrNo) {
        return Optional.ofNullable(bookingMap.get(pnrNo));
    }

     @Override
     public Double getCompletedBookingForFlight(Integer flightId) {
         return 0.0;
     }
 }
