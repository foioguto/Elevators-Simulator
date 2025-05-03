package dataStructure;

import run.Elevator;
import run.User;

public class PriorityQueue {

    static class Node {
        Node next;
        Node prev;
        User user;
        int priority; // priority 1 > priority 2

        Node(User user, int priority) {
            this.next = null;
            this.prev = null;
            this.user = user;
            this.priority = priority;
        }
    }

    private int size;
    private Node head;
    private Node tail;

    public PriorityQueue() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public void append(User user, int priority) {
        Node newNode = new Node(user, priority);

        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else if (newNode.priority < head.priority) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        else if (newNode.priority >= tail.priority) {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }else {
            Node current = head;

            for(int i = 0; i < getSize(); i++) {
                if (newNode.priority < current.priority) {
                    newNode.next = current;
                    newNode.prev = current.prev;
                    current.prev = newNode;
                }
                current = current.next;
            }
        }

        size++;
    }

    public void remove() {
        if (head == null) {
            return;
        }

        if (head.next == null) {
            head = null;
            tail = null;
        }
        else {
            head = head.next;
            head.prev.next = null;
        }

        size--;
    }

    public User moveToElevator(Elevator currentUsers) {
        User current = head.user;
        remove();
        return current;
    }

    public void print() {
        Node current = head;
        for (int i = 0; i < getSize(); i++) {
            System.out.print(current.user);
            current = current.next;
        }
        System.out.println();
    }


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
