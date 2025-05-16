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
    private int timeInHours = 0;
    private int totalPeople;

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
            totalPeople += numberOfUsers;

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

    public void startElevator(Elevator elevator) {
        elevator.setBuilding(building);
        Thread thread = new Thread(elevator);
        thread.start();
    
    }

    public void generateNewUserRequests() {
        for (int floorIndex = building.getTotalFloors() - 1; floorIndex >= 0; floorIndex--) {
            Floor floor = building.getFloor(floorIndex);
            UserQueue users = floor.getUsers();
            if (users.getFirst() != null) {
                EventsList eventsList = new EventsList();
                Random random = new Random();
                User current = users.getFirst();

                do {
                    current.setNextFloor(random.nextInt(0, (building.getTotalFloors() -3)));
                     if (current.getCurrentFloor() == 0 && current.getNextFloor() == 0) {
                        eventsList.setEvents(2);
                    }
                } while (current.getNextFloor() == current.getCurrentFloor());
            }
        }
    }

    public void simulateElevatorRuns(int times, Elevator elevator) {
        System.out.println("Starting simulation with " + times + " elevator cycles...\n");

        int i = timeInHours + parameters.START_TIME;
        startElevator(elevator);

        while (i < times) {
            System.out.println("CYCLE #" + timeInHours);

            setUsersBuilding();
            printBuildingState(building);
            
            if (elevators[0].getTotalTime() >= 60) { //just the first elevator counts the time 
                timeInHours++;
                elevators[0].resetTotalTime();
            }

            System.out.println("END OF CYCLE #" + timeInHours + "\n");
        }

        generateLogs(building);
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
            simulateElevatorRuns(parameters.END_TIME - parameters.START_TIME, building.getElevator(i));
        }
    }

    public void generateLogs(Building building) {
        int total = 0;
        for (int i = 0; i < building.getNumElevators(); i++) {
            System.out.println("Elevator " + elevators[i] + " Energy spent:" + elevators[i].getTotalEnergy());
            total += elevators[i].getTotalEnergy();
        }
        System.out.println("Total users transported: " + totalPeople);
        System.out.println("Total energy spent: " + total);
        System.out.println("Total cost: " + (total * parameters.COST_PER_KWH));
    }

    public void increasetTimeInHours() {
        timeInHours++;
    }

    public int getTimeInHours() {
        return timeInHours;
    }

    public Building getBuilding() {
        return building;
    }

    public int getTotalPeople() {
        return totalPeople;
    }

    public void setTotalPeople(int totalPeople) {
        this.totalPeople = totalPeople;
    }
}
