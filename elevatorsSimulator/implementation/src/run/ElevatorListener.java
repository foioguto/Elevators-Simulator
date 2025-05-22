package run;

import run.dataStructure.UserQueue;

public interface ElevatorListener {
    void onElevatorMoved(int elevatorNumber, int currentFloor, Elevator.ElevatorState state);
    void onUsersChanged(int elevatorNumber, UserQueue currentUsers, int currentFloor);
}
