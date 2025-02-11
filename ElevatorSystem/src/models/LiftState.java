package models;

public interface LiftState {
    /**
     * Initiates movement of the lift to the destination floor.
     * @param lift The lift instance.
     * @param destination The destination floor.
     */
    void moveTo(Lift lift, Floor destination);

    String getStateName();
}
