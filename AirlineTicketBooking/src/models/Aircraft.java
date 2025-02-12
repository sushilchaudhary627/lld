package models;

import java.util.ArrayList;
import java.util.List;

public class Aircraft {
    private Integer aircraftId;
    private String aircraftName;

    public Aircraft(Integer aircraftId, String aircraftName, Integer capacity, List<Seat> seats) {
        this.aircraftId = aircraftId;
        this.aircraftName = aircraftName;
        this.capacity = capacity;
        this.seats = seats;
    }

    public Aircraft(Integer aircraftId,String aircraftName,  Integer capacity) {
        this.aircraftId = aircraftId;
        this.aircraftName = aircraftName;
        this.capacity = capacity;
        this.seats = new ArrayList<>();
    }

    private Integer capacity;
    List<Seat> seats;

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public String getAircraftName() {
        return aircraftName;
    }

    public Integer getAircraftId() {
        return aircraftId;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "aircraftId=" + aircraftId +
                ", aircraftName='" + aircraftName + '\'' +
                ", capacity=" + capacity +
                ", seats=" + seats +
                '}';
    }
}
