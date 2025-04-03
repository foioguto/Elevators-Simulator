public class WaitingQueue {
    private User queue;
    private int size;
    private int front;
    private int rear;

    //TODO Fix the getOriginFloor() NullPointerException
    public WaitingQueue() {
        this.queue = new User(queue.getOriginFloor(), queue.getDestinationFloor(), queue.hasPriority());
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    public void addUser(User user) {
        // No idea
    }

    public User removeUser() {
        // no ikea
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
