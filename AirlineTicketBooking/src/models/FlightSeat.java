package models;

import constants.FlightSeatStatus;

public class FlightSeat {
    private final Integer flightSeatId;
    Seat seat;

    public FlightSeat(Integer flightSeatId, Seat seat, Double price, FlightSeatStatus flightSeatStatus) {
        this.flightSeatId = flightSeatId;
        this.seat = seat;
        this.price = price;
        this.flightSeatStatus = flightSeatStatus;
    }

    Double price;
    FlightSeatStatus flightSeatStatus;

    public FlightSeatStatus getFlightSeatStatus() {
        return flightSeatStatus;
    }

    public void setFlightSeatStatus(FlightSeatStatus flightSeatStatus) {
        this.flightSeatStatus = flightSeatStatus;
    }

    public Integer getFlightSeatId() {
        return flightSeatId;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "FlightSeat{" +
                "flightSeatId=" + flightSeatId +
                ", seat=" + seat +
                ", price=" + price +
                ", flightSeatStatus=" + flightSeatStatus +
                '}';
    }
}
