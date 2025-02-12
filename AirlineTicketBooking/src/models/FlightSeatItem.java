package models;

public class FlightSeatItem {
    public FlightSeatItem(FlightSeat flightSeat, Double price) {
        this.flightSeat = flightSeat;
        this.price = price;
    }

    public FlightSeat getFlightSeat() {
        return flightSeat;
    }

    public Double getPrice() {
        return price;
    }

    FlightSeat flightSeat;
    Double price;

}
