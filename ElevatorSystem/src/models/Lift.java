package models;

import models.states.IdleState;

public class Lift {
    private final Integer id;
    private Floor currentFloor;
    private final int maxCapacity;
    private int currentWeight;
    // Instead of a simple enum, we use a state instance.
    private LiftState state;

    public Lift(Integer id, Floor currentFloor, int maxCapacity) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.maxCapacity = maxCapacity;
        this.currentWeight = 0;
        // Initially, the lift is idle.
        this.state = new IdleState();
    }

    public Integer getId() {
        return id;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Floor floor) {
        this.currentFloor = floor;
    }

    public LiftState getState() {
        return state;
    }

    public void setState(LiftState state) {
        this.state = state;
    }

    public boolean canAccommodate(int weight) {
        return (currentWeight + weight) <= maxCapacity;
    }

    public void boardPassenger(int weight) {
        if (!canAccommodate(weight)) {
            throw new IllegalArgumentException("Lift " + id + " cannot accommodate weight " + weight);
        }
        currentWeight += weight;
    }

    public void alightPassenger(int weight) {
        currentWeight = Math.max(0, currentWeight - weight);
    }

    /**
     * Delegates the move operation to the current state.
     */
    public void moveTo(Floor destination) {
        state.moveTo(this, destination);
    }

    @Override
    public String toString() {
        return "Lift{" +
                "id=" + id +
                ", currentFloor=" + currentFloor.getFloorNo() +
                ", state=" + state.getStateName() +
                '}';
    }
}
