package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Booking {
    private Integer bookingId;
    private List<FlightSeatItem> flightSeatItems;
    private Flight flight;
    private LocalDateTime bookingDate;
    private User bookedBy;
    private String pnr;

    public Booking(Integer bookingId) {
        this.bookingId = bookingId;
        this.bookingDate = LocalDateTime.now();
        this.flightSeatItems = new ArrayList<>();
    }

    public void addFlightSeatItem(FlightSeatItem flightSeatItem){
        if(flightSeatItem == null){
            flightSeatItems = new ArrayList<>();
        }
        flightSeatItems.add(flightSeatItem);
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public User getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(User bookedBy) {
        this.bookedBy = bookedBy;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", flightSeatItems=" + flightSeatItems +
                ", flight=" + flight +
                ", bookingDate=" + bookingDate +
                ", bookedBy=" + bookedBy +
                ", pnr='" + pnr + '\'' +
                '}';
    }
}
