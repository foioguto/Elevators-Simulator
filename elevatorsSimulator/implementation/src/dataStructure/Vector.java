package dataStructure;

import run.User;

import java.util.Random;

/**
 * Represents the people inside the elevator
 * Manages users in the elevator and their destination requests.
 */
public class Vector {
    private int floor;
    private User[] users;
    private Random rand;

    /**
     * Constructs a new Vector with the specified vector number.
     * @param floor The floor number (0-based index)
     */
    public Vector(int floor) {
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
     * @throws IllegalArgumentException if usersQuantity is negative
     * @throws ArrayIndexOutOfBoundsException if users array isn't properly initialized
     */
    public void setUsers(int usersQuantity, int totalFloors) {
        if (usersQuantity < 0) {
            throw new IllegalArgumentException("User quantity cannot be negative");
        }

        this.users = new User[usersQuantity]; // Initialize array with proper size

        for (int i = 0; i < usersQuantity; i++) {
            boolean up = rand.nextBoolean();
            int destination;

            // Ensure destination is different from current floor
            do {
                if (up) {
                    destination = rand.nextInt(floor + 1, totalFloors);
                } else {
                    destination = rand.nextInt(0, floor);
                }
            } while (destination == floor);

            this.users[i] = new User(this.floor, destination, up);
        }
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

    public void getEveryoneInside(List list) {
        if (users == null || users.length == 0) {
            return;
        }

        for (User user : users) {
            if (user != null) {
                list.append(user);
            }
        }
    }

    // Getters and Setters

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