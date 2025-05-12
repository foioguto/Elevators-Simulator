package dataStructure;

import run.User;
import run.Elevator;
import java.util.Random;

public class Floor {
    private int floor;
    private UserQueue users;

    public Floor(int floor) {
        this.floor = floor;
        this.users = new UserQueue();
    }

    public void setUsers(UserQueue users) {
        this.users = users;
    }

    public void bringElevatorToFloor(User user) {
        users.append(user);
    }

    public void goToElevator(Elevator elevator) {
        while (!users.isEmpty()) {
            elevator.append(users.removeFirst());
        }
    }

    public boolean checkUpJoin() {
        UserQueue.UserNode current = users.getHead();
        while (current != null) {
            if (current.user.isUp()) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int getFloor() {
        return floor;
    }

    public UserQueue getUsers() {
        return users;
    }

    public int getSize() {
        return users.getSize();
    }

}
