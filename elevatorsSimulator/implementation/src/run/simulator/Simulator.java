package run.simulator;

import run.model.*;
import java.util.Random;

public class Simulator {
    private final Random random = new Random();
    private Building building;
    private Elevator elevator;

    // ==================== Initialization Methods ====================
    public void startBuildingRandom() {
        int floorsNumber = 5 + random.nextInt(5);
        this.building = new Building(floorsNumber);
        System.out.println("Building: " + floorsNumber);
    }

    public void startBuildingManual(int floorsNumber) {
        this.building = new Building(floorsNumber);
    }

    public void setElevators(int maxCapacity) {
        this.elevator = new Elevator(maxCapacity);
        building.setElevator(elevator);
    }

    // ==================== User Generation Methods ====================
    public void setUsersBuilding() {
        Random random = new Random();
        int totalFloors = building.getTotalFloors();
        Floor[] floors = building.getFloors();

        for (Floor floor : floors) {
            int currentFloor = floor.getFloor();
            UserQueue existingQueue = floor.getUsers();
            if (existingQueue == null) {
                existingQueue = new UserQueue();
                floor.setUsers(existingQueue);
            }

            int numberOfUsers = random.nextInt(3);

            for (int i = 0; i < numberOfUsers; i++) {
                int nextFloor;
                do {
                    nextFloor = random.nextInt(totalFloors);
                } while (nextFloor == currentFloor);

                boolean up = nextFloor > currentFloor;
                boolean priority = false;

                User user = new User(currentFloor, nextFloor, up, priority);
                existingQueue.append(user);
            }
        }
    }

    public void setUsersBuildingHot() {
        Random random = new Random();
        int totalFloors = building.getTotalFloors();
        Floor[] floors = building.getFloors();

        for (Floor floor : floors) {
            int currentFloor = floor.getFloor();
            UserQueue existingQueue = floor.getUsers();
            if (existingQueue == null) {
                existingQueue = new UserQueue();
                floor.setUsers(existingQueue);
            }

            int numberOfUsers = random.nextInt(6);

            for (int i = 0; i < numberOfUsers; i++) {
                int nextFloor;
                do {
                    nextFloor = random.nextInt(totalFloors);
                } while (nextFloor == currentFloor);

                boolean up = nextFloor > currentFloor;
                boolean priority = false;

                User user = new User(currentFloor, nextFloor, up, priority);
                existingQueue.append(user);
            }
        }
    }

    public void setUsersBuildingAlternate() {
        int totalFloors = building.getTotalFloors();
        Floor[] floors = building.getFloors();

        for (Floor floor : floors) {
            if (random.nextDouble() < 0.4) {
                int currentFloor = floor.getFloor();
                UserQueue existingQueue = floor.getUsers();

                int numberOfUsers = random.nextInt(2);

                for (int i = 0; i < numberOfUsers; i++) {
                    int nextFloor;
                    do {
                        nextFloor = random.nextInt(totalFloors);
                    } while (nextFloor == currentFloor);

                    boolean up = nextFloor > currentFloor;
                    boolean priority = false;

                    System.out.println("New Demand!");
                    System.out.println("Floor: " + currentFloor + " Destination: " + nextFloor);

                    User user = new User(currentFloor, nextFloor, up, priority);
                    existingQueue.append(user);
                }
            }
        }
    }

    public void generateNewUserRequests() {
        // Empty implementation
    }

    // ==================== Simulation Control Methods ====================
    public void startElevator() {
        elevator.move(building, this);
    }

    public void simulateElevatorRuns(int times) {
        System.out.println("Starting simulation with " + times + " elevator cycles...\n");

        for (int i = 1; i <= times; i++) {
            System.out.println("CYCLE #" + i);

            setUsersBuilding();

            printBuildingState(building);

            startElevator();

            System.out.println("END OF CYCLE #" + i + "\n");
        }

        System.out.println("Simulation completed.");
    }

    public void simulateElevatorRunsHot(int times) {
        System.out.println("Starting simulation with " + times + " elevator cycles...\n");

        for (int i = 1; i <= times; i++) {
            System.out.println("CYCLE #" + i);

            setUsersBuildingHot();

            printBuildingState(building);

            startElevator();

            System.out.println("END OF CYCLE #" + i + "\n");
        }

        System.out.println("Simulation completed.");
    }

    // ==================== Display Methods ====================
    public void printBuildingState(Building building) {
        System.out.println("===== Building State =====");

        for (int floorIndex = building.getTotalFloors() - 1; floorIndex >= 0; floorIndex--) {
            Floor floor = building.getFloor(floorIndex);
            UserQueue users = floor.getUsers();

            System.out.printf("Floor %2d | Users: ", floorIndex);
            if (users.getSize() == 0) {
                System.out.print("None");
            } else {
                for (User user : users) {
                    System.out.print(user.getNextFloor() + " ");
                }
            }
            System.out.println();
        }

        System.out.println("==========================\n");
    }
}