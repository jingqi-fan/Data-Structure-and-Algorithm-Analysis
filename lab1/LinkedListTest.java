package lab1;

import java.util.Iterator;

public class LinkedListTest {
    public static void main(String[] args) {
        // Create a new LinkedList
        LinkedList<String> list = new LinkedList<>();

        // Test adding elements to the front and back of the list
        list.addToFront("B");
        list.addToBack("C");
        list.addToFront("A");

        // Test getting the front and back elements of the list
        System.out.println("Front element of list: " + list.getFront()); // should print "A"
        System.out.println("Back element of list: " + list.getBack()); // should print "C"

        // Test removing elements from the front and back of the list
        String removedElement1 = list.removeFromFront();
        String removedElement2 = list.removeFromBack();

        // Test getting the front and back elements of the list after removals
        System.out.println("Front element of list after removals: " + list.getFront()); // should print "B"
        System.out.println("Back element of list after removals: " + list.getBack()); // should print "B"

        // Test adding and removing elements from an empty list
        LinkedList<Integer> emptyList = new LinkedList<>();

        try {
            emptyList.removeFromFront(); // should throw IllegalStateException
        } catch (IllegalStateException e) {
            System.out.println("Removing element from empty list correctly throws exception.");
        }

        emptyList.addToBack(1);
        Integer removedElement3 = emptyList.removeFromFront();

        if (removedElement3 != 1) {
            System.out.println("Error: Removed element from empty list is not what was expected.");
        }
    }
}
