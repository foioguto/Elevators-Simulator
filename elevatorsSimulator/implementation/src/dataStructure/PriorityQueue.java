/**
 * This package contains data structures for managing elevator simulations.
 */
package dataStructure;

/**
 * A priority queue implementation that manages users based on their priority.
 * The queue is implemented as a linked list where nodes are ordered by priority.
 */
public class PriorityQueue {
    /**
     * A node in the priority queue that holds a user and their priority.
     */
    class Node {
        User user;
        int priority;
        Node next;

        /**
         * Creates a new Node with the specified user and priority.
         * @param user The user to be stored in the node
         * @param priority The priority of the user
         */
        public Node(User user, int priority) {
            this.user = user;
            this.priority = priority;
        }

        /**
         * Gets the user stored in this node.
         * @return The user object
         */
        public User getuser() {
            return user;
        }

        /**
         * Sets the user for this node.
         * @param user The user to set
         */
        public void setUser(User user) {
            this.user = user;
        }

        /**
         * Gets the priority of this node.
         * @return The priority value
         */
        public int getPriority() {
            return priority;
        }

        /**
         * Sets the priority for this node.
         * @param priority The priority value to set
         */
        public void setPriority(int priority) {
            this.priority = priority;
        }
    }

    private Node first;
    private Node last;
    private int size;

    /**
     * Checks if the priority queue is empty.
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Inserts a new user into the priority queue with the specified priority.
     * The queue maintains nodes in priority order.
     * @param user The user to insert
     * @param priority The priority of the user
     * @return true if insertion was successful
     */
    public boolean insert(User user, int priority) {
        Node element = new Node(user, priority);
        if(isEmpty()) {
            first = element;
            last = element;
        } else {
            Node current = last;
            Node previous = null;
            for (int i = 0; i < size; i++) {
                if(current == first && current == last) {
                    if(element.priority < current.priority) {
                        first.next = element;
                        first = element;
                        break;
                    } else {
                        element.next = first;
                        last = element;
                    }
                }
                if(first.priority > element.priority) {
                    first.next = element;
                    first = element;
                }
                if(current.priority <= element.priority) {
                    if(last == current) {
                        element.next = current;
                        last = element;
                    } else {
                        if(previous != null) {
                            previous.next = element;
                        }
                        element.next = current;
                        break;
                    }
                } else {
                    previous = current;
                    current = current.next;
                }
            }
        }
        size++;
        return true;
    }

    /**
     * Removes and returns the highest priority node from the queue.
     * @return The removed node
     * @throws RuntimeException if the queue is empty
     */
    public Node remove() {
        Node current;
        Node previous;
        if(isEmpty()) {
            throw new RuntimeException("The queue is empty");
        } else if(first == last) {
            current = first;
            first = null;
            last = null;
        } else {
            current = last;
            previous = null;
            for(int i = 0; i < size; i++) {
                if(current == first) {
                    previous.next = null;
                    first = previous;
                    break;
                }
                previous = current;
                current = current.next;
            }
        }
        size--;
        return current;
    }

    /**
     * Returns the highest priority node without removing it.
     * @return The highest priority node, or null if queue is empty
     */
    public Node peek() {
        return first;
    }

    /**
     * Gets the current size of the priority queue.
     * @return The number of elements in the queue
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Clears all elements from the priority queue.
     * @return true if the operation was successful
     */
    public boolean clear() {
        first = null;
        last = null;
        this.size = 0;
        return true;
    }
}

/**
 * A queue implementation that manages users in a doubly-linked list structure.
 * This queue supports operations at both ends and provides elevator-specific functionality.
 */