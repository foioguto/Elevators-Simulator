public class Elevator {
    private int maxCapacity;
    private int currentFloor;
    private boolean isMoving;
    private boolean doorOpen;

    public Elevator(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.currentFloor = 0;
        this.isMoving = false;
        this.doorOpen = false;
    }

    public void moveTo(int targetFloor) {
        // Logic
    }

    public void openDoor() {
        // to
    }

    public void closeDoor() {
        // hold the door, please!!!
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public boolean isDoorOpen() {
        return doorOpen;
    }

    public void setDoorOpen(boolean doorOpen) {
        this.doorOpen = doorOpen;
    }
}
