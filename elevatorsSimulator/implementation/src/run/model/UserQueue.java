package run.model;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A doubly-linked list implementation for managing queues of Users
 * Supports standard queue operations and iteration
 */
public class UserQueue implements Iterable<User> {

    // ==================== Inner Node Class ====================

    /**
     * Represents a node in the doubly-linked list
     */
    public class UserNode {
        public User user;
        public UserNode next;
        public UserNode prev;

        /**
         * Creates a new node containing a User
         */
        public UserNode(User user) {
            this.user = user;
            this.next = null;
            this.prev = null;
        }
    }

    // ==================== Class Fields ====================

    private UserNode head;
    private UserNode tail;
    private int size;

    // ==================== Constructor ====================

    /**
     * Creates an empty UserQueue
     */
    public UserQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    // ==================== Queue Operations ====================

    /**
     * Adds a user to the end of the queue
     */
    public void append(User user) {
        UserNode newNode = new UserNode(user);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        incrementSize();
    }

    /**
     * Removes and returns the user at the front of the queue
     * @return The removed user or null if queue is empty
     */
    public User removeFirst() {
        if (head == null) {
            return null;
        }

        User removed = head.user;
        if (head.next == null) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }

        decrementSize();
        return removed;
    }

    /**
     * Clears all users from the queue
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // ==================== Status Checks ====================

    /**
     * Checks if the queue is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    // ==================== Size Management ====================

    /**
     * Gets current queue size
     */
    public int getSize() {
        return size;
    }

    /**
     * Decreases queue size by 1
     */
    public void decrementSize() {
        this.size--;
    }

    /**
     * Increases queue size by 1
     */
    public void incrementSize() {
        this.size++;
    }

    // ==================== Node Accessors ====================

    /**
     * Gets the first node in the queue
     */
    public UserNode getHead() {
        return head;
    }

    /**
     * Sets the first node in the queue
     */
    public void setHead(UserNode head) {
        this.head = head;
    }

    /**
     * Gets the last node in the queue
     */
    public UserNode getTail() {
        return tail;
    }

    /**
     * Sets the last node in the queue
     */
    public void setTail(UserNode tail) {
        this.tail = tail;
    }

    // ==================== Iteration Support ====================

    /**
     * Provides iterator for foreach support
     */
    @Override
    public Iterator<User> iterator() {
        return new Iterator<User>() {
            private UserNode current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public User next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                User user = current.user;
                current = current.next;
                return user;
            }
        };
    }
}