import dataEstructure.DLL;

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

    public boolean wantsToEnterHereUp(Floor floor){
        boolean ok = false;
        for(int i = 0; i < floor.getUsers().length; i++){
            if(floor.getUser(i).isUp()){
                ok = true;
            }
        }
        return ok;
    }

    public boolean wantsToExitHere(){
        return currentUsers.wantsToExitHere(currentFloor);
    }

    public void handleDoorsAtCurrentFloor(){

    }

    public boolean requestsAbove(Building building){
        boolean ok = false;
        for (int i = 0 ; i<building.getFloors().length; i++){
            if(i>currentFloor){
                if (building.getFloor(i).users != null){
                    ok = true;
                }
            }
        }
        return ok;
    }

    public boolean requestsBelow(Building building){
        boolean ok = false;
        for (int i = 0 ; i<building.getFloors().length; i++){
            if(i<currentFloor){
                if (building.getFloor(i).users != null){
                    ok = true;
                }
            }
        }
        return ok;
    }

    public boolean insideWantsToGoUp(){
        return currentUsers.insideWantsToGoUp();
    }

    public boolean insideWantsToGoDown(){
        return currentUsers.insideWantsToGoDown();
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
