package DataEstructure;

public class User {
    private int currentFloor;
    private int nextFloor;
    private boolean up;

    public User(int currentFloor, int nextFloor, boolean up) {
        this.currentFloor = currentFloor;
        this.nextFloor = nextFloor;
        this.up = up;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getNextFloor() {
        return nextFloor;
    }

    public void setNextFloor(int nextFloor) {
        this.nextFloor = nextFloor;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }
}
