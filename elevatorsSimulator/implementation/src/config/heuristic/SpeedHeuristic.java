package config.heuristic;

import run.Elevator;
import run.dataStructure.UserQueue;
import run.Building;

public class SpeedHeuristic implements MovementStyle {
    @Override
    public int decideNextFloor(Elevator elevator, Building building) {
        int currentFloor = elevator.getCurrentFloor();
        
        if (currentFloor >= building.getTotalFloors() / 2) {
            for (int i = building.getTotalFloors(); i >= 0; i--) {
                if (i == currentFloor) continue;

                UserQueue users = building.getFloor(i).getUsers();

                if (users != null && users.hasWaitingUsers()) {
                    return i;
                }
            }
        } else { 
            for (int i = 0; i < building.getTotalFloors(); i++) {
                if (i == currentFloor) continue;

                UserQueue users = building.getFloor(i).getUsers();

                if (users != null && users.hasWaitingUsers()) {
                    return i;
                }
            }
        }
        
        return currentFloor;
    }
    
}
