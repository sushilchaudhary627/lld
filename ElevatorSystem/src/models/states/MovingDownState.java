package models.states;

import models.Floor;
import models.Lift;
import models.LiftState;

public class MovingDownState implements LiftState {
    @Override
    public void moveTo(Lift lift, Floor destination) {
        System.out.println("Lift " + lift.getId() + " is moving down from floor "
                + lift.getCurrentFloor().getFloorNo() + " to floor " + destination.getFloorNo());
        lift.setCurrentFloor(destination);
        lift.setState(new IdleState());
    }

    @Override
    public String getStateName() {
        return "MOVING_DOWN";
    }
}