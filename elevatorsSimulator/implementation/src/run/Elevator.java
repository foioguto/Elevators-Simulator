package run;

import dataStructure.InternalPanel;
import dataStructure.Floor;
import dataStructure.UserQueue;

public class Elevator {
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
        long startTime = System.nanoTime();

        int directionCode = (state == ElevatorState.IDLE) ? ElevatorState.UP.getDirectionCode() : state.getDirectionCode();
        state = (directionCode > 0) ? ElevatorState.UP : ElevatorState.DOWN;

        while (isWithinBounds(building)) {
            logElevatorStatus("Before");

            Floor floor = building.getFloor(currentFloor);
            boolean wantsToEnter = wantsToEnterHere(floor, building);
            boolean wantsToExit = panel.wantsToExitHere(currentUsers, currentFloor);

            if (wantsToEnter || wantsToExit) {
                simulateDoorOperation();
                handleDoorsAtCurrentFloor(currentUsers, floor);
                simulatePassengerExchange();
                simulateDoorOperation();
            }

            logElevatorStatus("After");

            if (!hasRequestsInCurrentDirection(building, directionCode)) {
                if (hasRequestsInOppositeDirection(building, directionCode)) {
                    directionCode = -directionCode;
                    state = (directionCode > 0) ? ElevatorState.UP : ElevatorState.DOWN;
                    continue;
                } else {
                    stopElevator();
                    break;
                }
            }

            simulateTravelBetweenFloors();
            currentFloor += directionCode;
        }

        long endTime = System.nanoTime();
        double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.printf("\n=== Total Travel Time: %.3f seconds ===%n", durationInSeconds);
    }

    private boolean isWithinBounds(Building building) {
        return currentFloor >= 0 && currentFloor < building.getTotalFloors();
    }

    private void logElevatorStatus(String phase) {
        if (phase.equals("Before")) {
            System.out.println("\n================== Elevator Status ==================");
        }

        System.out.printf("%s | Floor: %d | Passengers: %d | State: %s%n",
                phase, currentFloor, currentUsers.getSize(), state);

        System.out.print("Passenger destinations: ");
        for (User user : currentUsers) {
            System.out.print(user.getNextFloor() + " ");
        }
        System.out.println();

        if (phase.equals("After")) {
            System.out.println("=====================================================");
        }
    }

    private boolean hasRequestsInCurrentDirection(Building building, int directionCode) {
        return (directionCode > 0 && (requestsAbove(building) || panel.insideWantsToGoUp(currentUsers, currentFloor))) ||
                (directionCode < 0 && (requestsBelow(building) || panel.insideWantsToGoDown(currentUsers, currentFloor)));
    }

    private boolean hasRequestsInOppositeDirection(Building building, int directionCode) {
        return (directionCode > 0 && (requestsBelow(building) || panel.insideWantsToGoDown(currentUsers, currentFloor))) ||
                (directionCode < 0 && (requestsAbove(building) || panel.insideWantsToGoUp(currentUsers, currentFloor)));
    }

    private void stopElevator() {
        state = ElevatorState.IDLE;
        stop();
    }

    public void stop() {
        state = ElevatorState.IDLE;
    }

    public boolean wantsToEnterHere(Floor floor, Building building) {
        for (User user : floor.getUsers()) {
            if (user != null) {
                if (state == ElevatorState.IDLE) {
                    return true;
                }

                if (state == ElevatorState.UP && user.isUp()) {
                    return true;
                }

                if (state == ElevatorState.DOWN && !user.isUp()) {
                    return true;
                }

                if (currentFloor == building.getTotalFloors() - 1 && !user.isUp()) {
                    return true;
                }

                if (currentFloor == 0 && user.isUp()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void handleDoorsAtCurrentFloor(UserQueue currentUsers, Floor floor) {
        panel.detectExitRequests(currentUsers, this.currentFloor);
        floor.goToElevator(this);
    }

    public boolean requestsAbove(Building building) {
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

    private void simulateDoorOperation() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void simulatePassengerExchange() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void simulateTravelBetweenFloors() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
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
