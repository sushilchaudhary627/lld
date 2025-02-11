package models;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private Integer buildingId;
    private List<Lift> lifts;
    private List<Floor>floors;
    private String name;
    public Building(Integer buildingId, List<Lift> lifts) {
        this.buildingId = buildingId;
        this.lifts = lifts;
    }

    public Building(Integer buildingId, String name) {
        this.buildingId  = buildingId;
        this.name = name;
        this.lifts = new ArrayList<>();
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public List<Lift> getLifts() {
        return lifts;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public void addNewLift(Lift lift) {
        lifts.add(lift);
    }

    @Override
    public String toString() {
        return "Building{" +
                "buildingId=" + buildingId +
                ", lifts=" + lifts +
                ", name='" + name + '\'' +
                '}';
    }
}
