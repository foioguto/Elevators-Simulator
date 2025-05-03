package run;

import dataStructure.InternalPanel;
import dataStructure.Floor;
import dataStructure.UserQueue;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an elevator in a building simulation.
 * Manages elevator movement, user entry/exit, and direction requests.
 */
public class Elevator extends  UserQueue{
    private final int maxCapacity;
    private final InternalPanel panel;
    private ElevatorState state;
    private UserQueue currentUsers;
    private int currentFloor;
    private boolean priority;

    public enum ElevatorState {
        IDLE(0),
        UP(1),
        DOWN(-1);

        private final int directionCode;

        ElevatorState(int directionCode) {
            this.directionCode = directionCode;
        }

        public int getDirectionCode() {
            return directionCode;
        }
    }

    public Elevator(int maxCapacity, boolean priority) {
        this.currentFloor = 0;
        this.state = ElevatorState.IDLE;
        this.maxCapacity = maxCapacity;
        this.priority = priority;
        this.currentUsers = new UserQueue();
        this.panel = new InternalPanel();
    }

    public void move(Building building) {

        int direction = (state == ElevatorState.IDLE) ? ElevatorState.UP.getDirectionCode() : state.getDirectionCode();
        state = (direction > 0) ? ElevatorState.UP : ElevatorState.DOWN;

        while (currentFloor >= 0 && currentFloor < building.getTotalFloors()) {
            Floor floor = building.getFloor(currentFloor);
            System.out.printf("Current Floor: %d, Users: %d, State: %s%n", currentFloor, currentUsers.getSize(), state);

            boolean wantsToEnter = wantsToEnterHere(floor);
            boolean wantsToExit = panel.wantsToExitHere(currentUsers, currentFloor);

            if (wantsToEnter || wantsToExit) {
                handleDoorsAtCurrentFloor(currentUsers, floor);
            }

            boolean hasFurtherRequests = (direction > 0)
                    ? requestsAbove(building) || panel.insideWantsToGoUp(currentUsers, currentFloor)
                    : requestsBelow(building) || panel.insideWantsToGoDown(currentUsers, currentFloor);

            if (!hasFurtherRequests) {
                direction = -direction;
                state = (direction > 0) ? ElevatorState.UP : ElevatorState.DOWN;
            }

            currentFloor += direction;
        }

        stop();
    }

    public void stop() {
        state = ElevatorState.IDLE;
    }

    public boolean wantsToEnterHere(@NotNull Floor floor) {
        for (User user : floor.getUsers()) {
            if (user != null) {
                if (state == ElevatorState.UP && user.isUp()) return true;
                if (state == ElevatorState.DOWN && !user.isUp()) return true;
            }
        }
        return false;
    }

    public void handleDoorsAtCurrentFloor(@NotNull UserQueue currentUsers, Floor floor) {
        panel.detectExitRequests(currentUsers, this.currentFloor);
        floor.goToElevator(this);
    }

    public boolean requestsHere(@NotNull Building building) {
        UserQueue users = building.getFloor(currentFloor).getUsers();
        if (users != null) {
            for (User user : users) {
                if (user != null) return true;
            }
        }
        return false;
    }

    public boolean requestsAbove(@NotNull Building building) {
        for (int i = currentFloor + 1; i < building.getFloors().length; i++) {
            UserQueue users = building.getFloor(i).getUsers();
            if (users != null) {
                for (User user : users) {
                    if (user != null) return true;
                }
            }
        }
        return false;
    }

    public boolean requestsBelow(Building building) {
        for (int i = 0; i < currentFloor; i++) {
            UserQueue users = building.getFloor(i).getUsers();
            if (users != null) {
                for (User user : users) {
                    if (user != null && !user.isUp()) return true;
                }
            }
        }
        return false;
    }

    // Getters and Setters

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public UserQueue getCurrentUsers() {
        return currentUsers;
    }

    public void setCurrentUsers(UserQueue currentUsers) {
        this.currentUsers = currentUsers;
    }

    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }
}
