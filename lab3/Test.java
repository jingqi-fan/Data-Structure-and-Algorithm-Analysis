import java.util.Random;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        BinarySearchTree bst = new BinarySearchTree();
//        Scanner sc = new Scanner(System.in);
//        System.out.println("enter the number of nodes that you want: ");
//        int num_to_insert = sc.nextInt();


        // Insert the strings
        bst.insert("Mercury");
        bst.insert("Venus");
        bst.insert("Earth");
        bst.insert("Mars");
        bst.insert("Jupiter");
        bst.insert("Saturn");
        bst.insert("Uranus");
        bst.insert("Neptune");



        //Print the tree details
        System.out.println(bst.toString());

        // Test remove method
        System.out.println("Removing Uranus...");
        bst.remove("Uranus");
        System.out.println(bst.toString());

        System.out.println("Removing Mercury...");
        bst.remove("Mercury");
        System.out.println(bst.toString());


    }
}

