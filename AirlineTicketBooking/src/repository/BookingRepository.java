package repository;

import models.Booking;

import java.util.Optional;

public interface BookingRepository {
    public void save(Booking booking);
    public Optional<Booking> getBookingByPnr(String pnrNo);

    Double getCompletedBookingForFlight(Integer flightId);
}
