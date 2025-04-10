package og;

public class User {
    private Floor IntendedFloor;
    private boolean Up;
    private boolean Priority;

    public User(Floor intendedFloor, boolean up, boolean priority) {
        IntendedFloor = intendedFloor;
        Up = up;
        Priority = priority;
    }

}
