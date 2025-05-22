package run.model;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A doubly-linked list implementation for managing collections of Users.
 * Supports standard list operations, iteration, and efficient insertion/deletion at both ends.
 */
public class UserList implements Iterable<User> {

    // ==================== Inner Node Class ====================

    /**
     * Represents a node in the doubly-linked list.
     * Contains references to previous and next nodes for bidirectional traversal.
     */
    public class UserNode {
        /** The User data contained in this node */
        public User user;

        /** Reference to the next node in the list */
        public UserNode next;

        /** Reference to the previous node in the list */
        public UserNode prev;

        /**
         * Constructs a new node with the given User.
         * @param user The User object to store in this node
         */
        public UserNode(User user) {
            this.user = user;
            this.next = null;
            this.prev = null;
        }
    }

    // ==================== Class Fields ====================

    /** Reference to the first node in the list */
    private UserNode head;

    /** Reference to the last node in the list */
    private UserNode tail;

    /** Current number of elements in the list */
    private int size;

    // ==================== Constructor ====================

    /**
     * Constructs an empty doubly-linked User list.
     */
    public UserList() {
        head = null;
        tail = null;
        size = 0;
    }

    // ==================== List Operations ====================

    /**
     * Appends a user to the end of the list.
     * @param user The User to add to the list
     */
    public void append(User user) {
        UserNode newNode = new UserNode(user);
        if (head == null) {
            // First element in empty list
            head = newNode;
            tail = newNode;
        } else {
            // Append to existing list
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        incrementSize();
    }

    /**
     * Removes and returns the first user in the list.
     * @return The removed User, or null if list is empty
     */
    public User removeFirst() {
        if (head == null) {
            return null;
        }

        User removed = head.user;
        if (head.next == null) {
            // Removing only element
            head = null;
            tail = null;
        } else {
            // Remove head and update references
            head = head.next;
            head.prev = null;
        }

        decrementSize();
        return removed;
    }

    /**
     * Clears all users from the list.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // ==================== List Status ====================

    /**
     * Checks if the list contains no elements.
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    // ==================== Size Management ====================

    /**
     * Gets the current number of elements in the list.
     * @return The size of the list
     */
    public int getSize() {
        return size;
    }

    /**
     * Decreases the list size by 1.
     * Should be called after removal operations.
     */
    protected void decrementSize() {
        this.size--;
    }

    /**
     * Increases the list size by 1.
     * Should be called after addition operations.
     */
    protected void incrementSize() {
        this.size++;
    }

    // ==================== Node Accessors ====================

    /**
     * Gets the first node in the list.
     * @return Reference to the head node, or null if empty
     */
    public UserNode getHead() {
        return head;
    }

    /**
     * Sets the first node in the list.
     * @param head The new head node
     */
    public void setHead(UserNode head) {
        this.head = head;
    }

    /**
     * Gets the last node in the list.
     * @return Reference to the tail node, or null if empty
     */
    public UserNode getTail() {
        return tail;
    }

    /**
     * Sets the last node in the list.
     * @param tail The new tail node
     */
    public void setTail(UserNode tail) {
        this.tail = tail;
    }

    // ==================== Iteration Support ====================

    /**
     * Provides an iterator for sequential access to the Users in the list.
     * @return An Iterator<User> for foreach support
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