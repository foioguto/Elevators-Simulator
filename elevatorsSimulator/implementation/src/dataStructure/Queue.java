/**
 * This package contains data structures for managing elevator simulations.
 */
package dataStructure;

import run.User;

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
