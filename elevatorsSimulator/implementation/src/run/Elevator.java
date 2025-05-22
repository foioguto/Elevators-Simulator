package run;

import java.util.Random;

public class Elevator {
    private final int maxCapacity;
    private final InternalPanel intPanel;
    private final ExternalPanel extPanel;
    private ElevatorState state;
    private UserQueue currentUsers;
    private int currentFloor;
    private double energyUsed = 0.0;
    double simulatedTime = 0.0;

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

    public Elevator(int maxCapacity) {
        this.currentFloor = 0;
        this.state = ElevatorState.IDLE;
        this.maxCapacity = maxCapacity;
        this.currentUsers = new UserQueue();
        this.intPanel = new InternalPanel();
        this.extPanel = new ExternalPanel();
    }

    public void move(Building building, Simulator simulator) {
        long startTime = System.nanoTime();

        int directionCode = (state == ElevatorState.IDLE) ? ElevatorState.UP.getDirectionCode() : state.getDirectionCode();
        state = (directionCode > 0) ? ElevatorState.UP : ElevatorState.DOWN;

        while (isWithinBounds(building)) {
            for (int i = 0; i < 50; ++i) System.out.println();
            simulator.printBuildingState(building);

            logElevatorStatus("Before");

            Floor floor = building.getFloor(currentFloor);
            boolean wantsToEnter = floor.getExtPanel().wantsToEnterHere(floor, building, building.getElevator());
            boolean wantsToExit = intPanel.wantsToExitHere(currentUsers, currentFloor);

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

            if (new Random().nextDouble() < 0.2) {
                simulator.setUsersBuildingAlternate();
            }

            simulateTravelBetweenFloors();
            if (directionCode > 0) {
                energyUsed += 2.0;
            } else {
                energyUsed += 1.2;
            }
            currentFloor += directionCode;
        }

        long endTime = System.nanoTime();
        double durationInSeconds = ((endTime - startTime) / 1_000_000_000.0)*3;
        int minutes = (int) (durationInSeconds / 60);
        int seconds = (int) (durationInSeconds % 60);
        System.out.printf("\nTotal Travel Time: %02dmin%02ds %n", minutes, seconds);
        System.out.printf("Energia Consumida: %.2f unidades %n", energyUsed);
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

        StringBuilder inside = new StringBuilder();
        for (User user : currentUsers) {
            inside.append(String.format("| %2d ", user.getNextFloor()));
        }
        if (inside.length() == 0) {
            inside.append("|     ");
        }
        inside.append("|");

        int width = inside.length();
        String border = "+" + "-".repeat(width - 2) + "+";

        System.out.println(border);
        System.out.println(inside);
        System.out.println(border);

        if (phase.equals("After")) {
            System.out.println("=====================================================");
        }
    }

    private boolean hasRequestsInCurrentDirection(Building building, int directionCode) {
        return (directionCode > 0 && (requestsAbove(building) || intPanel.insideWantsToGoUp(currentUsers, currentFloor))) ||
                (directionCode < 0 && (requestsBelow(building) || intPanel.insideWantsToGoDown(currentUsers, currentFloor)));
    }

    private boolean hasRequestsInOppositeDirection(Building building, int directionCode) {
        return (directionCode > 0 && (requestsBelow(building) || intPanel.insideWantsToGoDown(currentUsers, currentFloor))) ||
                (directionCode < 0 && (requestsAbove(building) || intPanel.insideWantsToGoUp(currentUsers, currentFloor)));
    }

    private void stopElevator() {
        state = ElevatorState.IDLE;
    }

    public void handleDoorsAtCurrentFloor(UserQueue currentUsers, Floor floor) {
        intPanel.detectExitRequests(currentUsers, this.currentFloor);
        boardPassengers(floor);
    }

    private void boardPassengers(Floor floor) {
        UserQueue floorQueue = floor.getUsers();
        while (!floorQueue.isEmpty() && currentUsers.getSize() < maxCapacity) {
            currentUsers.append(floorQueue.removeFirst());
        }
    }

    public boolean requestsAbove(Building building) {
        for (int i = currentFloor + 1; i < building.getTotalFloors(); i++) {
            UserQueue users = building.getFloor(i).getUsers();
            if (users != null && !users.isEmpty()) {
                return true;
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

}
