public class Queue<E> {
    private LinkedList<E> list; // Using your LinkedList implementation

    public Queue() {
        list = new LinkedList<>();
    }

    // i. Add data at the end of the list (enqueue)
    public void enqueue(E data) {
        list.insertAtBack(data); // Matches your LinkedList's method for adding at the back
    }

    // ii. Remove data at the beginning of the list (dequeue)
    public E dequeue() {
        return list.removeFirst(); // Matches your LinkedList's method for removing the first element
    }

    // iii. Determine the size of the list
    public int size() {
        return list.size(); // Directly uses the size() method from your LinkedList
    }

    // iv. Determine whether the list is empty
    public boolean isEmpty() {
        return list.isEmpty(); // Directly uses the isEmpty() method from your LinkedList
    }
    
    public void sortRequestDate()
    {
        list.sortRequestDate();
    }
}
