package models.states;

import models.*;

public class IdleState implements LiftState {
    @Override
    public void moveTo(Lift lift, Floor destination) {
        // Decide the direction based on current and destination floor.
        if (destination.getFloorNo() > lift.getCurrentFloor().getFloorNo()) {
            lift.setState(new MovingUpState());
        } else if (destination.getFloorNo() < lift.getCurrentFloor().getFloorNo()) {
            lift.setState(new MovingDownState());
        }
        // Delegate movement to the current state.
        // here you can simulate floor based on sleep
        // other thread can check for available lifts
        lift.setCurrentFloor(destination);
        lift.setState(new IdleState());
    }

    @Override
    public String getStateName() {
        return "IDLE";
    }
}
