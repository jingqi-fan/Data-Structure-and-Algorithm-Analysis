package lab1;

public class Node<E> {
    private E data;
    private Node<E> next;

    public Node() {
        // No-argument constructor
    }

    public Node(E theData) {
        data = theData;
    }

    public Node(E theData, Node<E> theNext) {
        data = theData;
        next = theNext;
    }

    // Getter and Setter methods for data and next

    public E getData() {
        return data;
    }

    public void setData(E newData) {
        data = newData;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> newNext) {
        next = newNext;
    }

    // toString method to convert data to a string
    @Override
    public String toString() {
        return String.valueOf(data);
    }
}

