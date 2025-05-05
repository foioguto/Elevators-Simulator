package dataStructure;

import run.User;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class UserQueue implements Iterable<User> {

    static class UserNode {
        public User user;
        public UserNode next;
        public UserNode prev;

        public UserNode(User user) {
            this.user = user;
            this.next = null;
            this.prev = null;
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

    public void append(User user) {
        UserNode newNode = new UserNode(user);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            UserNode current = head;
            for (int i = 0; i < getSize(); i++) {
                if (current == tail) {
                    current.next = newNode;
                    newNode.prev = current;
                    tail = newNode;
                    break;
                }
                current = current.next;
            }
        }

        incrementSize();
        return;
    }

    public User removeFirst() {
        User removed = head.user;

        if (head == null) {
            return null;
        }
        if (head.next == null) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.next.prev = null;
            head.prev = null;

        }

        decrementSize();
        return removed;
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
