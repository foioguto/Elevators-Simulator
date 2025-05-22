package run;
import config.ElevatorController;
import config.Parameters;
import run.dataStructure.Array;
import run.dataStructure.UserQueue;
import run.panels.InternalPanel;

public class Elevator implements Runnable{
    private Building building;
    private final int maxCapacity;
    private final InternalPanel intPanel;
    private ElevatorState state;
    private UserQueue currentUsers;
    private int currentFloor;
    private int elevatorNumber;
    private int totalEnergy;
    private int totalTime;
    private ElevatorController elevatorController;
    private volatile boolean running = true; // controls the loop thread
    private Array<ElevatorListener> listeners;


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

    public Elevator(int maxCapacity, int elevatorNumber) {
        this.currentFloor = 0;
        this.state = ElevatorState.IDLE;
        this.maxCapacity = maxCapacity;
        this.currentUsers = new UserQueue();
        this.intPanel = new InternalPanel();
        this.totalEnergy = 0;
        this.totalTime = 0;
        this.elevatorNumber = elevatorNumber;
        this.setElevatorController(ElevatorController.chooseHeuristic());
        listeners = new Array<>(100);
    }

    public void move(Building building) {
        long startTime = System.nanoTime();

        while (running) {
            logElevatorStatus("Before");
            Floor floor = building.getFloor(currentFloor);
            boolean wantsToEnter = floor.getExtPanel().wantsToEnterHere(floor, building, this);
            boolean wantsToExit = intPanel.wantsToExitHere(currentUsers, currentFloor);

            int nextFloor = elevatorController.decideNextFloor(this, building);
            if (nextFloor == currentFloor) {
                stopElevator();
                break;
            }

            int direction = Integer.compare(nextFloor, currentFloor);
            this.state = direction > 0 ? ElevatorState.UP : ElevatorState.DOWN;

            while (currentFloor != nextFloor) {
                simulateTravelBetweenFloors();
                currentFloor += direction;
            }
            
            if (wantsToEnter || wantsToExit) {
                simulateDoorOperation();

                handleDoorsAtCurrentFloor(currentUsers, floor);
                simulatePassengerExchange();

                simulateDoorOperation();
                logElevatorStatus("After");
            }    
        }

        long endTime = System.nanoTime();
        double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.printf("\n=== Total Travel Time: %.3f seconds ===%n", durationInSeconds);
    }

    private void logElevatorStatus(String phase) {
        if (phase.equals("Before")) {
            System.out.println("\n================== Elevator Status ==================");
        }

        System.out.printf("%s |Elevator: %d | Floor: %d | Passengers: %d | State: %s%n",
                phase, elevatorNumber, currentFloor, currentUsers.getSize(), state);

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

    public void stopElevator() {
        state = ElevatorState.IDLE;
    }

    public void handleDoorsAtCurrentFloor(UserQueue currentUsers, Floor floor) {
        intPanel.exitElevator(currentUsers, this.currentFloor);
        boardPassengers(floor);
    }

    private void boardPassengers(Floor floor) {
        UserQueue floorQueue = floor.getUsers();
        while (!floorQueue.isEmpty() && currentUsers.getSize() < maxCapacity) {
            User user = floorQueue.removeFirst();
            user.setWaiting(false); // Crie isso na classe User
            currentUsers.append(user, floorQueue.getPriority());
        }
    }

    public boolean requestsAbove(Building building) {
        for (int i = currentFloor + 1; i < building.getTotalFloors(); i++) {
            UserQueue users = building.getFloor(i).getUsers();
            if (users != null && users.hasWaitingUsers()) {
              return true;
            }
        }
        return false;
    }

    public boolean requestsBelow(Building building) {
        for (int i = 0; i < currentFloor; i++) {
            UserQueue users = building.getFloor(i).getUsers();
            if (users != null && users.hasWaitingUsers()) {
               return true;
            }
        }
        return false;
    }

    public void addListener(ElevatorListener listener) {
        listeners.append(listener);
    }

    private void notifyListeners() {
        for (int i = 0;i<listeners.size();i++) {
            listeners.get(i).onElevatorMoved(elevatorNumber, currentFloor, state);
            listeners.get(i).onUsersChanged(elevatorNumber, currentUsers, currentFloor);
        }
    }

    private void simulateDoorOperation() {
        try {
            Thread.sleep(Parameters.DELAY);

            increaseEnergy();
            increaseTime();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void simulatePassengerExchange() {
        try {
            Thread.sleep(Parameters.DELAY);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void simulateTravelBetweenFloors() {
        try {
            Thread.sleep(Parameters.DELAY);

            increaseEnergy();
            increaseTime();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public int increaseTime() {
        return totalTime += Parameters.TIME;
    }

    public synchronized int increaseEnergy() {
        return totalEnergy += Parameters.ENERGY_CONSUMPTION;
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

    public void resetTotalTime() {
        this.totalTime = 0;
    }

    public int getElevatorNumber() {
        return this.elevatorNumber;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void stopElevatorRun() {
        this.running = false;
    }

    public void setElevatorController(ElevatorController elevatorController) {
        this.elevatorController = elevatorController;
    }

    @Override
    public void run() {
        while (running) {
            move(this.building);
            notifyListeners();
        }
    }
}
