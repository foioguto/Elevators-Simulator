package og;

public class Building {
    private Floor[] floors;
    private Elevator elevator1;

    public Building(int floorsQuantity, Elevator elevator1) {
        this.floors = new Floor[floorsQuantity];
        this.elevator1 = elevator1;
    }

}
