package run.model;

import run.panel.ExternalPanel;

public class Floor {
    private int floor;
    private UserQueue users;
    private ExternalPanel extPanel;

    public Floor(int floor) {
        this.floor = floor;
        this.users = new UserQueue();
        this.extPanel = new ExternalPanel();
    }

    public void setUsers(UserQueue users) {
        this.users = users;
    }

    public void bringElevatorToFloor(User user) {
        users.append(user);
    }

    public void goToElevator(Elevator elevator) {
        while (!users.isEmpty()) {
            elevator.getCurrentUsers().append(users.removeFirst());
        }
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

    public ExternalPanel getExtPanel() {
        return extPanel;
    }

}
