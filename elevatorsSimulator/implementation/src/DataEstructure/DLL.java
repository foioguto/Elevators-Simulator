package DataEstructure;

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

    int size;

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
            size++;
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
            size++;
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
            size--;
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

        public boolean wantsToExitHere(int actualFloor){
            Node current = head;
            boolean ok = false;
            for (int i= 0; i < size; i++) {
                if(current.user.getNextFloor()==actualFloor){
                    return true;
                }
                current = current.next;
            }
            return ok;
        }

        public boolean insideWantsToGoUp(){
            Node current = head;
            boolean ok = false;
            for (int i= 0; i < size; i++) {
                if(current.user.isUp()){
                    return true;
                }
                current = current.next;
            }
            return ok;
        }

        public boolean insideWantsToGoDown(){
            Node current = head;
            boolean ok = true;
            for (int i= 0; i < size; i++) {
                if(current.user.isUp()){
                    return false;
                }
                current = current.next;
            }
            return ok;
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
