package models;

public class Seat {
    private Integer seatId;
    private String displayText;

    public Seat(Integer seatId, String displayText) {
        this.seatId = seatId;
        this.displayText = displayText;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "displayText='" + displayText + '\'' +
                '}';
    }
}
