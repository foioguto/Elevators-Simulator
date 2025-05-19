package simulator.events;

import java.util.Scanner;
import run.Building;
import run.Floor;
import run.User;
import run.dataStructure.UserQueue;

public class BuildingEvents {
    private Building building;
    private Scanner scanner;

    public BuildingEvents(Building building) {
        this.building = building;
        this.scanner = new Scanner(System.in);
        this.setScanner(scanner);
    }

      public void startBuildingManual(int MAX_FLOORS) {
        this.building = new Building(MAX_FLOORS);
    }

    public void printBuildingState() {
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
        System.out.println("Press any key to continue...");
        scanner.next();
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
