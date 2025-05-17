package simulator.events;

import run.Building;
import run.Floor;
import run.User;
import run.UserQueue;
import java.util.Random;
import config.parameters;
import simulator.EventsList;

public class UserEvents {
    private int totalPeople = 0;
    private Building building;

    public UserEvents(Building building) {
        this.building = building;
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
                boolean isWaiting = true;

                User user = new User(currentFloor, nextFloor, up, priority, isWaiting);
                userQueue.append(user, priority);
            }

            floor.setUsers(userQueue);
        }
    }

    public void generateNewUserRequests() {
        for (int floorIndex = building.getTotalFloors() - 1; floorIndex >= 0; floorIndex--) {
            Floor floor = building.getFloor(floorIndex);
            UserQueue users = floor.getUsers();

            if (users.getFirst() != null) {
                EventsList eventsList = new EventsList();
                Random random = new Random();
                User current = users.getFirst();

                for (int i = 0; i < users.getSize(); i++) {
                    do {
                        current.setNextFloor(random.nextInt(0, building.getTotalFloors() - 3));
                        if (current.getCurrentFloor() == 0 && current.getNextFloor() == 0) {
                            eventsList.setEvent("exitBuilding");
                            eventsList.callEvent();
                        }
                    } while (current.getNextFloor() == current.getCurrentFloor());
                    current.setWaiting(true);
                }
            }
        }
    }

    public int getTotalPeople() {
        return totalPeople;
    }

    public void setTotalPeople(int totalPeople) {
        this.totalPeople = totalPeople;
    }
}
