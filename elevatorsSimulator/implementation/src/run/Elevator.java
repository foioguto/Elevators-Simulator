package run;
import config.parameters;

public class Elevator {
    private final int maxCapacity;
    private final InternalPanel intPanel;
    private ElevatorState state;
    private UserQueue currentUsers;
    private int currentFloor;
    private int totalEnergy;
    private int totalTime;

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
        this.totalEnergy = 0;
        this.totalTime = 0;
    }

    public void move(Building building) {
        long startTime = System.nanoTime();

        int directionCode = (state == ElevatorState.IDLE) ? ElevatorState.UP.getDirectionCode() : state.getDirectionCode();
        state = (directionCode > 0) ? ElevatorState.UP : ElevatorState.DOWN;

        while (isWithinBounds(building)) {
            logElevatorStatus("Before");

            Floor floor = building.getFloor(currentFloor);
            boolean wantsToEnter = floor.getExtPanel().wantsToEnterHere(floor, building, getElevator());
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
            currentUsers.append(floorQueue.removeFirst(), floorQueue.getPriority());
        }
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

            increaseEnergy();
            increaseTime();
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

            increaseEnergy();
            increaseTime();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public int increaseTime() {
        return totalTime += parameters.TIME;
    }

    public int increaseEnergy() {
        return totalEnergy += parameters.ENERGY_CONSUMPTION;
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
    
    public InternalPanel getIntPanel() {
        return intPanel;
    }
    public int getTotalEnergy() {
        return totalEnergy;
    }   
    public int getTotalTime() {
        return totalTime;
    }

    public Elevator getElevator() {
        return this;
    }

}
