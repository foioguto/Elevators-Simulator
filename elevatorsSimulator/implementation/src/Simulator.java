import run.Floor;
import run.UserQueue;
import run.Building;
import run.Elevator;
import run.User;
import java.util.Random;
import config.parameters;

public class Simulator {
    private Building building;
    private Elevator[] elevators;
    private int timeInHours;

    public void startBuildingManual(int MAX_FLOORS) {
        this.building = new Building(MAX_FLOORS);
    }

    public void setUsersBuilding() {
        Random random = new Random();
        int totalFloors = building.getTotalFloors();
        Floor[] floors = building.getFloors();

        for (Floor floor : floors) {
            int currentFloor = floor.getFloor();
            UserQueue userQueue = new UserQueue();

            int numberOfUsers = random.nextInt(8);

            for (int i = 0; i < numberOfUsers; i++) {
                int nextFloor;

                do {
                    nextFloor = random.nextInt(totalFloors);
                } while (nextFloor == currentFloor);

                boolean up = nextFloor > currentFloor;
                int setPrio = random.nextInt(parameters.PRIORITY_RARITY);
                int priority = setPrio < parameters.PRIORITY_RARITY ? 2 : 1;

                User user = new User(currentFloor, nextFloor, up, priority);
                userQueue.append(user, priority);
            }

            floor.setUsers(userQueue);
        }
    }

    public void generateElevators(int maxCapacity, int MAX_ELEVATORS) {
        this.elevators = new Elevator[MAX_ELEVATORS];
        for (int i = 0; i < MAX_ELEVATORS; i++) {
            this.elevators[i] = new Elevator(maxCapacity, i);
        }
        building.setElevators(elevators);
    }

    public void startElevator() {
        for (Elevator elevator : elevators) {
            elevator.setBuilding(building);
            Thread thread = new Thread(elevator);
            thread.start();
        }
    }

    public void generateNewUserRequests() {
        for (int floorIndex = building.getTotalFloors() - 1; floorIndex >= 0; floorIndex--) {
            Floor floor = building.getFloor(floorIndex);
            UserQueue users = floor.getUsers();
            if (users.getFirst() != null) {
                Random random = new Random();
                User current = users.getFirst();
                do {
                    current.setNextFloor(random.nextInt(building.getTotalFloors()));
                } while (current.getNextFloor() == current.getCurrentFloor());
            }
        }
    }

    public void simulateElevatorRuns(int times, int elevatorNumber) {
        System.out.println("Starting simulation with " + times + " elevator cycles...\n");

        while (timeInHours < times) {
            int i = timeInHours + parameters.START_TIME;
            System.out.println("CYCLE #" + i);

            setUsersBuilding();
            printBuildingState(building);
            startElevator();

            if (elevators[elevatorNumber].getTotalTime() % 60 == 0) {
                timeInHours++;
            }

            System.out.println("END OF CYCLE #" + i + "\n");
        }

        System.out.println("Simulation completed.");
    }

    private void printBuildingState(Building building) {
        System.out.println("===== Initial Building State =====");

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

        System.out.println("==================================\n");
    }

    public void startRun (Building building) {
        for (int i = 0; i < building.getNumElevators(); i++) {
            simulateElevatorRuns(parameters.END_TIME - parameters.START_TIME, building.getElevator(i).getElevatorNumber());
        }
    }

    public void setTimeInHours(int timeInHours) {
        this.timeInHours = timeInHours;
    }

    public int getTimeInHours() {
        return timeInHours;
    }

    public Building getBuilding() {
        return building;
    }
}
