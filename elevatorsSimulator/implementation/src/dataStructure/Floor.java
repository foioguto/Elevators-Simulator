package dataStructure;

import run.User;

import java.util.Random;

/**
 * Represents the people inside the elevator.
 * Manages users on a floor and their destination requests.
 */
public class Floor {
    private int floor;
    private User[] users;
    private Random rand;

    /**
     * Constructs a new Floor object with the specified floor number.
     * @param floor The floor number (0-based index)
     */
    public Floor(int floor) {
        this.floor = floor;
        this.rand = new Random(); // Initialize Random here for consistent usage
        this.users = new User[0]; // Initialize empty array to prevent NPE
    }

    /**
     * Generates random users waiting on this floor.
     * Each user is created with random destination and direction.
     *
     * @param usersQuantity The number of users to generate
     * @param totalFloors The total number of floors in the building (for destination bounds)
     * @param actualFloor The floor where these users are generated
     * @throws IllegalArgumentException if usersQuantity is negative or totalFloors < 2
     */
    public void setUsers(int usersQuantity, int totalFloors, int actualFloor) {
        if (usersQuantity < 0) {
            throw new IllegalArgumentException("User quantity cannot be negative");
        }

        if (totalFloors < 2) {
            throw new IllegalArgumentException("There must be at least 2 floors to have valid destinations.");
        }

        this.users = new User[usersQuantity];
        Random rand = new Random();

        for (int i = 0; i < usersQuantity; i++) {
            int destinationFloor;

            do {
                destinationFloor = rand.nextInt(totalFloors);
            } while (actualFloor == destinationFloor);

            boolean up = destinationFloor > actualFloor;
            this.users[i] = new User(actualFloor, destinationFloor, up);
        }
    }

    /**
     * Adds additional users to empty spots in the current users array.
     *
     * @param quantity Number of users to add
     * @param totalFloors Total number of floors in the building
     * @param currentFloor The current floor of this object
     */
    public void setAdditionalUsers(int quantity, int totalFloors, int currentFloor) {
        for (int i = 0; i < users.length && quantity > 0; i++) {
            if (users[i] == null) {
                int destination;
                do {
                    destination = rand.nextInt(totalFloors);
                } while (destination == currentFloor);

                boolean direction = destination > currentFloor;
                users[i] = new User(currentFloor, destination, direction);
                quantity--;
            }
        }
    }

    /**
     * Moves all users from this floor into the elevator (linked list).
     * After being moved, each user slot is set to null.
     *
     * @param doubleLinkedList The elevator's internal user list
     */
    public void getEveryoneInside(DoubleLinkedList doubleLinkedList) {
        if (users == null || users.length == 0) {
            return;
        }

        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                doubleLinkedList.append(users[i]);
                users[i] = null;
            }
        }
    }

    /**
     * Checks if any users on this floor want to go up.
     * @return true if at least one user wants to go up, false otherwise
     */
    public boolean checkUpJoin() {
        if (users == null || users.length == 0) {
            return false;
        }

        for (User user : users) {
            if (user != null && user.isUp()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets all users currently waiting on this floor.
     * @return Array of User objects waiting on this floor
     */
    public User[] getUsers() {
        return users;
    }

    /**
     * Gets a specific user by index.
     * @param i The index of the user to retrieve
     * @return The User object at the specified index
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    public User getUser(int i) {
        return users[i];
    }

    /**
     * Gets the floor number.
     * @return The floor number
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Sets the floor number.
     * @param floor The new floor number
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }
}