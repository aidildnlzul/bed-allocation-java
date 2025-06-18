public class LinkedList<E> {
    private Node<E> head, tail;
    private Node<E> current;
    private int size;

    public LinkedList() {
        head = current = tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }
    
    //1
    public void insertAtFront(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.next = this.head;
        this.head = newNode;
        if (this.tail == null) {
            this.tail = this.head;
        }
        size++;
    }
    
    //1
    public void insertAtBack(E data) {
        Node<E> newNode = new Node<>(data);
        if (this.tail == null) {
            this.head = this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = this.tail.next;
        }
        size++;
    }

    public E getFirst() {
        if (this.isEmpty()) {
            return null;
        } else {
            this.current = this.head;
            return this.current.data;
        }
    }

    public E getLast() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.tail.data;
        }
    }
    
    //3
    public E getNext() {
        if (this.current == this.tail) {
            return null;
        } else {
            this.current = this.current.next;
            return this.current.data;
        }
    }
    
    //3
    public void getHead() {
        Node<E> temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public E removeFirst() {
        if (this.isEmpty()) {
            return null;
        } else {
            this.current = this.head;
            this.head = this.head.next;
            if (this.head == null) {
                this.tail = null;
            }
            size--;
            return current.data;
        }
    }

    public E removeLast() {
        if (this.isEmpty()) {
            return null;
        } else if (this.head == this.tail) {
            this.current = this.head;
            this.head = this.tail = null;
            size--;
            return current.data;
        } else {
            this.current = this.head;
            while (this.current.next != tail) {
                this.current = this.current.next;
            }
            Node<E> temp = this.tail;
            this.tail = this.current;
            this.tail.next = null;
            size--;
            return temp.data;
        }
    }
    
    //2
    public boolean removeNode(E data) {
        if (this.isEmpty()) {
            return false;
        }
        if (head.data.equals(data)) {
            removeFirst();
            return true;
        }
        Node<E> previous = null;
        Node<E> current = head;
        while (current != null && !current.data.equals(data)) {
            previous = current;
            current = current.next;
        }
        if (current != null) {
            previous.next = current.next;
            if (current == tail) {
                tail = previous;
            }
            size--;
            return true;
        }
        return false;
    }
    
    //4
    public int size() {
        return size;
    }
    
    //5&6
    public void display() {
        if (isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            System.out.println("Elements in the list:");
            getHead();
        }
    }
    
    public void sortRequestDate() {
        boolean swapped = true; // Flag to check if we need to swap again
        while (swapped) {
            swapped = false;
            Node <E> current = head;
    
            // Traverse through the list
            while (current != null && current.next != null) {
                // Check if the current and next nodes' data are instances of Vaccine
                if (current.data instanceof Bed && current.next.data instanceof Bed) {
                    Bed currentBed = (Bed) current.data;
                    Bed nextBed = (Bed) current.next.data;
    
                    // Compare the expiration dates of the two vaccines
                    if (currentBed.getRequestDate().compareTo(nextBed.getRequestDate()) > 0) {
                        // Swap the data between the two nodes
                        E temp = current.data; //The data of the current node is temporarily stored in the variable temp.
                        current.data = current.next.data; //Replace current.data with current.next.data
                        current.next.data = temp; //Assign the original current.data (stored in temp) to current.next.data
                        swapped = true; // A swap was made, so we need to check again
                    }
                }
                current = current.next; // Move to the next node in the list
            }
        }
    }
}
