package og;

public class PriorityQueue {
    class Node{
        int data;
        int priority;
        Node next;

        public Node(int data, int priority){
            this.data = data;
            this.priority = priority;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }
    }

    private Node first;
    private Node last;
    private int size;

    public boolean isEmpty(){
        return first == null;
    }

    public boolean insert(int data, int priority){
        Node element = new Node(data, priority);
        if(isEmpty()){
            first = element;
            last = element;
        }else {
            Node current = last;
            Node previous = null;
            for (int i=0;i<size;i++){
                if(current == first && current == last){
                    if(element.priority<current.priority){
                        first.next = element;
                        first = element;
                        break;
                    }else {
                        element.next = first;
                        last = element;
                    }
                }
                if(first.priority > element.priority){
                    first.next = element;
                    first = element;
                }
                if(current.priority <= element.priority){
                    if(last == current){
                        element.next = current;
                        last = element;
                    }else{
                        if(previous != null){
                            previous.next = element;
                        }
                        element.next = current;
                        break;
                    }
                }else{
                    previous = current;
                    current = current.next;
                }
            }
        }
        size ++;
        return true;
    }

}
