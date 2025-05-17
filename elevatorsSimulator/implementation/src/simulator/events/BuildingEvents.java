package simulator.events;

import run.Building;
import run.Floor;
import run.User;
import run.UserQueue;

public class BuildingEvents {
    private Building building;

    public BuildingEvents(Building building) {
        this.building = building;
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
    }
}
