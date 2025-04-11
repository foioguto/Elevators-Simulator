package og;

import og.DataEstructure.DLL;

public class Elevator{
    private int currentFloor;
    private boolean up;
    private boolean down;
    private boolean moving;
    private DLL currentUsers = new DLL();

    //Methods

    public void moveUp(){

    }

    public void moveDown(){

    }

    public void wantsToEnterHere(){

    }

    public void wantsToExitHere(){

    }

    public void handleDoorsAtCurrentFloor(){

    }

    public void requestsAbove(){

    }

    public void requestsBelow(){

    }

    public void insideWantsToGoUp(){

    }

    public void insideWantsToGoDown(){

    }









    //Getters and Setters

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public DLL getCurrentUsers() {
        return currentUsers;
    }

    public void setCurrentUsers(DLL currentUsers) {
        this.currentUsers = currentUsers;
    }
}
