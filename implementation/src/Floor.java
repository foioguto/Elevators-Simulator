public class Floor {
    private int number;
    public WaitingQueue waitingQueue;

    public Floor(int number) {
        this.number = number;
        this.waitingQueue = new WaitingQueue();
    }

    public void addUser(User user) {
        waitingQueue.addUser(user);
    }

    public User removeUser() {
        return waitingQueue.removeUser();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
