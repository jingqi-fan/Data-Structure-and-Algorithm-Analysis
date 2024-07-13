package lab1;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Iterable<E>{
    private Node<E> first = new Node<>();
    private int size;

    public boolean isEmpty() {
        return first.getNext() == null;
    }

    @Override
    public String toString() {
        StringBuilder tempString = new StringBuilder();
        Node<E> current = first.getNext();

        while (current != null) {
            tempString.append(current.getData()).append("\n");
            current = current.getNext();
        }

        return tempString.toString();
    }

    public int length() {
        int count = 0;
        Node<E> current = first.getNext();

        while (current != null) {
            count++;
            current = current.getNext();
        }

        return count;
    }

    public void addToFront(E data) {
        Node<E> newNode = new Node<>(data);

        if (isEmpty()) {
            first.setNext(newNode);
        } else {
            newNode.setNext(first.getNext());
            first.setNext(newNode);
        }
    }

    public E removeFromFront() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Linked list is empty");
        }

        Node<E> currentFirst = first.getNext();

        if (currentFirst.getNext() == null) {
            // Only one element in the list
            first.setNext(null);
        } else {
            first.setNext(currentFirst.getNext());
        }

        return currentFirst.getData();
    }

    public void addToBack(E element) {
        Node<E> newNode = new Node<>(element);
        Node<E> current = first;

        while (current.getNext() != null) {
            current = current.getNext();
        }

        current.setNext(newNode);
    }

    public E removeFromBack() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot remove from an empty list.");
        }

        Node<E> current = first;
        Node<E> previous = null;

        while (current.getNext() != null) {
            previous = current;
            current = current.getNext();
        }

        // Remove the last node
        previous.setNext(null);

        return current.getData();
    }

    public Node<E> find(E element) {
        Node<E> current = first.getNext();
        int index = 0;

        while (current != null) {
            if (current.getData().equals(element)) {
                return current; // Found the element, return the node
            }
            current = current.getNext();
            index++;
        }

        return null; // Element not found, return null
    }

    public E getFront() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot get element from an empty list.");
        }
        return first.getNext().getData();
    }

    public E getBack() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot get element from an empty list.");
        }

        Node<E> current = first.getNext();
        while (current.getNext() != null) {
            current = current.getNext();
        }
        return current.getData();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LinkedList)) {
            return false;
        }

        LinkedList<E> otherList = (LinkedList<E>) obj;

        if (this.length( ) != otherList.length( )) return false;


        Node<E> current1 = first.getNext();
        Node<E> current2 = otherList.first.getNext();

        while (current1 != null) {
            if (!current1.getData().equals(current2.getData())) {
                return false;
            }
            current1 = current1.getNext();
            current2 = current2.getNext();
        }

        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E> {
        private Node<E> current = first;

        public boolean hasNext() {
            return current.getNext() != null;
        }

        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            current = current.getNext();
            return current.getData();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
