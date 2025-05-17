package run;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class UserQueue implements Iterable<User> {

    static class UserNode {
        public User user;
        public UserNode next;
        public UserNode prev;
        int priority; // priority 1 > priority 2

        public UserNode(User user, int priority) {
            this.user = user;
            this.next = null;
            this.prev = null;
            this.priority = priority;
        }
    }


    private UserNode head;
    private UserNode tail;
    private int size;

    public UserQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    public synchronized void append(User user, int priority) {
        UserNode newNode = new UserNode(user, priority);

        if (head == null) {
            head = newNode;
            tail = newNode;
        }else {
            UserNode current = head;

            while (current != null) {
                if (newNode.priority < current.priority) {
                    newNode.next = current;
                    newNode.prev = current.prev;

                    if (current.prev != null) {
                        current.prev.next = newNode;
                    }
                    current.prev = newNode;

                    if (current == head) {
                        head = newNode;
                    }

                    incrementSize();
                    return;
                }

                current = current.next;
            }

            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            incrementSize();
        }
    }


    public synchronized User removeFirst() {
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
    
      public synchronized User removeLast() {
        if (head == null) {
            return null;
        }

        User removed = tail.user;

        if (tail.prev == null) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }

        decrementSize();
        return removed;
    }

    public boolean hasWaitingUsers() {
    for (User user : this) {
        if (user.isWaiting() == true) {
            return true;
        }
    }
    return false;
}


    public boolean isEmpty() {
        return size == 0;
    }

    public UserNode getHead() {
        return head;
    }

    public void setHead(UserNode head) {
        this.head = head;
    }

    public UserNode getTail() {
        return tail;
    }

    public void setTail(UserNode tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void decrementSize() {
        this.size--;
    }

    public void incrementSize() {
        this.size++;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public int getPriority() {
        if (head == null) {
            return -1;
        }
        return head.priority;
    }

    public User getFirst() {
        if (head == null) {
            return null;
        }
        return head.user;
    }

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
