package og;

public class Floor {
    private int floorNumber;
    private PriorityQueue userQueue = new PriorityQueue();

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void addUser(Floor intendedFloor, boolean up, boolean priority){
        User user = new User(intendedFloor,up,priority);
        if (priority){
            userQueue.insert(user,1);
        }else {
            userQueue.insert(user,2);
        }
    }

}
