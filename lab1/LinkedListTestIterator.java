package lab1;

import java.util.Iterator;

public class LinkedListTestIterator {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.addToBack("A");
        list.addToBack("B");
        list.addToBack("C");

        // Test removal methods
        String frontElem = list.getFront();
        String backElem = list.getBack();
        list.removeFromBack();
        list.removeFromBack();
        String frontAfterRemovals = list.getFront();
        String backAfterRemovals = list.getBack();

        System.out.println("Front element of list: " + frontElem);
        System.out.println("Back element of list: " + backElem);
        System.out.println("Front element of list after removals: " + frontAfterRemovals);
        System.out.println("Back element of list after removals: " + backAfterRemovals);

        // Test iterator
        Iterator<String> iterator = list.iterator();
        System.out.println("Using iterator to iterate over list:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

