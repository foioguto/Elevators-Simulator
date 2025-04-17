public class Building {
    private int totalFloors;
    private Floor[] floors;
    private Elevator elevator1;
    private Elevator elevator2;

    public Building(int totalFloors) {
        this.totalFloors = totalFloors;
    }

    public void setFloors(int floors){
        for (int i = 0; i < floors; i++) {
            this.floors[i] = new Floor(i);
        }
    }

    public Floor[] getFloors() {
        return floors;
    }

    public Floor getFloor(int i){
        return floors[i];
    }
}
