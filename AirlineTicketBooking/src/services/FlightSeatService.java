package services;

import constants.FlightSeatStatus;
import models.FlightSeat;

import java.util.List;

public class FlightSeatService {
    public void checkAvailabilityAndMarkUnAvailable(List<FlightSeat> flightSeats){
        if(!checkAvailability(flightSeats)){
            throw new IllegalArgumentException("Few seats are unavailable.");
        }
        for(FlightSeat flightSeat:flightSeats){
            flightSeat.setFlightSeatStatus(FlightSeatStatus.BOOKED);
        }
    }

    private Boolean checkAvailability(List<FlightSeat> flightSeats){
        return flightSeats.stream().allMatch((FlightSeat flightSeat) -> flightSeat.getFlightSeatStatus().equals(FlightSeatStatus.AVAILABLE));
    }

    public void MarkAvailable(List<FlightSeat> flightSeats) {
        for(FlightSeat flightSeat:flightSeats){
            flightSeat.setFlightSeatStatus(FlightSeatStatus.AVAILABLE);
        }
    }
}
