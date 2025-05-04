import dataStructure.Floor;
import run.Building;
import run.Elevator;
import run.User;
import dataBank.ConnectionDB;

import java.util.Random;

/**
 * Controls the simulation of the elevator system in a building.
 * Handles random or manual initialization, user generation and elevator cycles.
 */
public class Simulator {
    private final Random random = new Random();
    private Building building;
    private Elevator elevator;

    /**
     * Initializes the building with a random number of floors (between 2 and 4).
     */
    public void startBuildingRandom() {
        int floorsNumber = 5 + random.nextInt(5); // 2 to 4 floors
        this.building = new Building(floorsNumber);
        System.out.println("Building: " + floorsNumber);
    }

    /**
     * Initializes the building with a specified number of floors.
     * @param floorsNumber the number of floors to create
     */
    public void startBuildingManual(int floorsNumber) {
        this.building = new Building(floorsNumber);
    }

    /**
     * Populates each floor with a random number of users (up to 3).
     */
    public void setUsersBuilding() {
        ConnectionDB postgres = new ConnectionDB();
        postgres.start();
        Floor f1 = new Floor(1);
        f1.setUsers(3, 5, 1);
    }

    /**
     * Initializes the elevator and sets it in the building.
     */
    public void setElevators(int maxCapacity, boolean priority) {
        this.elevator = new Elevator(maxCapacity, priority); // max capacity = 8
        building.setElevator(elevator);
    }

    /**
     * Starts the elevator simulation by moving it upward.
     */
    public void startElevator() {
        elevator.move(building);
    }

    /**
     * Adds new random user requests on each floor, without removing existing users.
     */
    public void generateNewUserRequests() {
        for (int i = 0; i < building.getFloors().length; i++) {
            Floor floor = building.getFloors()[i];
            User newUser = new User(0, 1, true);
            floor.bringElevatorToFloor(newUser);
        }
    }

    /**
     * Runs multiple cycles of elevator movement and user generation.
     * @param times Number of elevator cycles to simulate
     */
    public void simulateElevatorRuns(int times) {
        System.out.println("Starting simulation with " + times + " elevator cycles...\n");

        for (int i = 1; i <= times; i++) {
            System.out.println("CYCLE #" + i);

            generateNewUserRequests();

            startElevator();

            System.out.println("END OF CYCLE #" + i + "\n");
        }

        System.out.println("Simulation completed.");
    }

}
