package simulator.events;

import run.Building;
import run.Floor;
import run.User;
import run.dataStructure.Array;
import run.dataStructure.UserQueue;
import config.Parameters;
import simulator.EventsList;
import java.util.Random;

/**
 * Handles all user-related events in the elevator simulation including:
 * - User generation
 * - User queue management
 * - Priority handling
 * - User movement between floors
 */
public class UserEvents {
    private int totalPeople = 0;
    private final Building building;
    private final Random random = new Random();

    /**
     * Constructs a UserEvents handler for the specified building.
     *
     * @param building the building where users will be generated and managed
     * @throws IllegalArgumentException if building is null
     */
    public UserEvents(Building building) {
        if (building == null) {
            throw new IllegalArgumentException("Building cannot be null");
        }
        this.building = building;
    }

    /**
     * Generates users across all floors of the building with random destinations.
     * Users are created with random priorities and added to floor queues.
     */
    public void setUsersBuilding() {
        int totalFloors = building.getTotalFloors();
        Array<Floor> floors = building.getFloors();

        for (int i = 0; i < floors.size(); i++) {
            Floor floor = floors.get(i);
            int currentFloor = floor.getFloor();
            UserQueue userQueue = new UserQueue();

            int numberOfUsers = random.nextInt(8);
            totalPeople += numberOfUsers;

            for (int j = 0; j < numberOfUsers; j++) {
                int nextFloor;
                do {
                    nextFloor = random.nextInt(totalFloors);
                } while (nextFloor == currentFloor);

                boolean goingUp = nextFloor > currentFloor;
                int priority = random.nextInt(Parameters.PRIORITY_RARITY) < Parameters.PRIORITY_RARITY ? 2 : 1;
                int waitTime = priority == 1 ? Parameters.QUEUE_TIME / 2 : Parameters.QUEUE_TIME;

                User user = new User(currentFloor, nextFloor, goingUp, priority, true, waitTime);
                userQueue.append(user, priority);
            }

            floor.setUsers(userQueue);
        }
    }

    /**
     * Generates new random destination requests for existing users.
     * Handles special case for ground floor exits.
     */
    public void generateNewUserRequests() {
        for (int floorIndex = building.getTotalFloors() - 1; floorIndex >= 0; floorIndex--) {
            Floor floor = building.getFloor(floorIndex);
            UserQueue users = floor.getUsers();

            if (!users.isEmpty()) {
                EventsList eventsList = new EventsList();
                User current = users.getFirst();

                for (int i = 0; i < users.getSize(); i++) {
                    do {
                        current.setNextFloor(random.nextInt(building.getTotalFloors() - 3));
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

    /**
     * Gets the total number of people generated in the simulation.
     *
     * @return total number of people generated
     */
    public int getTotalPeople() {
        return totalPeople;
    }

    /**
     * Sets the total number of people (for simulation reset purposes).
     *
     * @param totalPeople new total people count
     * @throws IllegalArgumentException if totalPeople is negative
     */
    public void setTotalPeople(int totalPeople) {
        if (totalPeople < 0) {
            throw new IllegalArgumentException("Total people cannot be negative");
        }
        this.totalPeople = totalPeople;
    }
}