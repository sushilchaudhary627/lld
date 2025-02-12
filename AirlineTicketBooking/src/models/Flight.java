package models;
import constants.FlightStatus;

import java.time.LocalDateTime;
import java.util.*;

public class Flight {
    private Integer flightId;
    List<FlightSeat>flightSeats;
    City from;
    City to;
    LocalDateTime departureAt;
    Integer durationInMin;
    Aircraft aircraft;
    FlightStatus flightStatus;
    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    Double price;

    public Flight(Integer flightId, List<FlightSeat> flightSeats, City from, City to, LocalDateTime departureAt, Integer durationInMin) {
        this.flightId = flightId;
        this.flightSeats = flightSeats;
        this.from = from;
        this.to = to;
        this.departureAt = departureAt;
        this.durationInMin = durationInMin;
    }

    public LocalDateTime getDepartureAt() {
        return departureAt;
    }

    public Integer getDurationInMin() {
        return durationInMin;
    }

    public List<FlightSeat> getFlightSeats() {
        return flightSeats;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", flightSeats=" + flightSeats +
                ", from=" + from +
                ", to=" + to +
                ", departureAt=" + departureAt +
                ", durationInMin=" + durationInMin +
                ", aircraft=" + aircraft.getAircraftName() +
                ", price=" + price +
                '}';
    }

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }
}
