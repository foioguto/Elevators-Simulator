package og.DataEstructure;

import og.User;

public class DLL {
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
    
        Node head;
        Node tail;

        // Add a node to the end of the list
        public void append(User user) {
            Node newNode = new Node(user);
            if (head == null) {
                head = tail = newNode;
                return;
            }
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        // Add a node to the beginning of the list
        public void prepend(User user) {
            Node newNode = new Node(user);
            if (head == null) {
                head = tail = newNode;
                return;
            }
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        // Delete a node with a specific user
        public void delete(User user) {
            Node current = head;

            while (current != null) {
                if (current.user == user) {
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
                    return;
                }
                current = current.next;
            }
        }

        // Print the list forward
        public void printForward() {
            Node current = head;
            System.out.print("Forward: ");
            while (current != null) {
                System.out.print(current.user + " ");
                current = current.next;
            }
            System.out.println();
        }

        // Print the list backward
        public void printBackward() {
            Node current = tail;
            System.out.print("Backward: ");
            while (current != null) {
                System.out.print(current.user + " ");
                current = current.prev;
            }
            System.out.println();
        }
    
}
