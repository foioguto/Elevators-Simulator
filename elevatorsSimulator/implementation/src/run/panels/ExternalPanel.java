package run.panels;

import run.Building;
import run.Elevator;
import run.Floor;
import run.User;

public class ExternalPanel {
    public ExternalPanel() {
    }

    public boolean wantsToEnterHere(Floor floor, Building building, Elevator elevator) {
        for (User user : floor.getUsers()) {
            if (user != null) {
                if (elevator.getState() == Elevator.ElevatorState.IDLE) {
                    return true;
                }

                if (elevator.getState() == Elevator.ElevatorState.UP && user.isUp()) {
                    return true;
                }

                if (elevator.getState() == Elevator.ElevatorState.DOWN && !user.isUp()) {
                    return true;
                }

                if (elevator.getCurrentFloor() == building.getTotalFloors() - 1 && !user.isUp()) {
                    return true;
                }

                if (elevator.getCurrentFloor() == 0 && user.isUp()) {
                    return true;
                }
            }
        }
        return false;
    }
}