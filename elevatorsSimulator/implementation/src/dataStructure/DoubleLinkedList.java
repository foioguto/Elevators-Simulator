package dataStructure;

import run.User;

/**
 * Represents a queue of users on a floor using a doubly linked list.
 */
public class DoubleLinkedList {

    /**
     * Node represents each user in the queue.
     */
    class Node {
        User user;
        Node next;
        Node prev;

        Node(User user) {
            this.user = user;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Adds a user to the end of the queue.
     *
     * @param user The user to add.
     * @return true if the user was added.
     */
    public boolean append(User user) {
        Node newNode = new Node(user);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    /**
     * Removes the first occurrence of a user from the queue.
     *
     * @param user The user to remove.
     * @return true if the user was found and removed.
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

    /**
     * Removes all users whose destination is the current floor.
     *
     * @param currentFloor The floor the elevator is currently on.
     */
    public void detectExitRequests(int currentFloor) {
        Node current = head;

        while (current != null) {
            Node nextNode = current.next;

            if (current.user.getNextFloor() == currentFloor) {
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
            }

            current = nextNode;
        }
    }

    // Getters and Setters

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }
}
