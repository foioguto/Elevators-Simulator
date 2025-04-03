public class User {
    private int originFloor;
    private int destinationFloor;
    private boolean priority; // Have he/she taken the "PIX"?

    public User(int originFloor, int destinationFloor, boolean priority) {
        this.originFloor = originFloor;
        this.destinationFloor = destinationFloor;
        this.priority = priority;
    }

    public int getOriginFloor() {
        return originFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public boolean hasPriority() {
        return priority;
    }
}
