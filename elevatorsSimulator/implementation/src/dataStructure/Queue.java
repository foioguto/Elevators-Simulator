/**
 * This package contains data structures for managing elevator simulations.
 */
package dataStructure;

public class Queue {
    /**
     * A node in the queue that holds a user and links to adjacent nodes.
     */
    class Node {
        User user;
        Node next;
        Node prev;

        /**
         * Creates a new Node with the specified user.
         * @param user The user to be stored in the node
         */
        Node(User user) {
            this.user = user;
            this.next = null;
            this.prev = null;
        }
    }

    Node head;
    Node tail;
    int size;

    public Queue() {
        this.head = null;
        this.tail = null;
        size = 0;
    }


    /**
     * Adds a user to the end of the queue.
     * @param user The user to add
     */

    //TODO check this method
    public boolean append(User user) {
        Node newNode = new Node(user);
        if (head == null) {
            head = tail = newNode;
        }
        Node actual = new Node(user);
        actual = head;
        while(actual.next != null) {
            actual = actual.next;
        }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
        return true;
    }

    /**
     * Adds a user to the beginning of the queue.
     * @param user The user to add
     */
    public void prepend(User user) {
        Node newNode = new Node(user);
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
        size++;
    }

    /**
     * Deletes the first occurrence of the specified user from the queue.
     * @param user The user to delete
     */
    public boolean delete(User user) {
        Node current = head;

        while (current != null) {
            if (current.user.equals(user)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }

                size--;
                return true;
            }

            current = current.next;
        }
        return false;
    }

    /*
     * Prints the queue from head to tail.
     */
    public void printForward() {
        Node current = head;
        System.out.print("Forward: ");
        while (current != null) {
            System.out.print(current.user + " ");
            current = current.next;
        }
        System.out.println();
    }

    /**
     * Prints the queue from tail to head.
     */
    public void printBackward() {
        Node current = tail;
        System.out.print("Backward: ");
        while (current != null) {
            System.out.print(current.user + " ");
            current = current.prev;
        }
        System.out.println();
    }

    /**
     * Checks if any user in the queue wants to exit at the specified floor.
     * @param actualFloor The floor to check
     * @return true if at least one user wants to exit at this floor
     */
    public boolean wantsToExitHere(int actualFloor) {
        Node current = head;
        for (int i = 0; i < size; i++) {
            if(current.user.getNextFloor() == actualFloor) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Checks if any user in the queue wants to go up.
     * @return true if at least one user wants to go up
     */
    public boolean insideWantsToGoUp() {
        Node current = head;
        for (int i = 0; i < size; i++) {
            if(current.user.isUp()) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Checks if all users in the queue want to go down.
     * @return true if all users want to go down
     */
    public boolean insideWantsToGoDown() {
        Node current = head;
        for (int i = 0; i < size; i++) {
            if(current.user.isUp()) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    /**
     * Gets the current size of the queue.
     * @return The number of elements in the queue
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the size of the queue (use with caution).
     * @param size The new size value
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Gets the head node of the queue.
     * @return The head node
     */
    public Node getHead() {
        return head;
    }

    /**
     * Sets the head node of the queue.
     * @param head The new head node
     */
    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * Gets the tail node of the queue.
     * @return The tail node
     */
    public Node getTail() {
        return tail;
    }

    /**
     * Sets the tail node of the queue.
     * @param tail The new tail node
     */
    public void setTail(Node tail) {
        this.tail = tail;
    }
}

/*
  Represents a user in the elevator simulation system.
  Stores information about the user's current floor, destination floor, and direction.
 */