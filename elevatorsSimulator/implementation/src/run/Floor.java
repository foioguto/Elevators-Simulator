package run;

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
        users.append(user, user.getPriority());
    }

    public void goToElevator(Elevator elevator) {
        while (!users.isEmpty()) {
            elevator.getCurrentUsers().append(users.removeFirst(), users.getPriority());
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
