public class BinarySearchTree {
    private static final int DEFAULT_MAX = 10;
    private Node[] nodes;
    private int root = -1;
    private int firstFree = 0;

    public BinarySearchTree() {
        nodes = new Node[DEFAULT_MAX];
    }

    public BinarySearchTree(int size) {
        nodes = new Node[size];
    }

    public void insert(String data) {
        if (root == -1) {
            nodes[firstFree] = new Node(data, -1, -1);
            root = firstFree;
            firstFree++;
        } else {
            insertRecursive(data, root);
        }
    }

    private void insertRecursive(String data, int currentIndex) {
        Node currentNode = nodes[currentIndex];

        if (data.compareTo(currentNode.getData()) < 0) {
            if (currentNode.getLeft() == -1) {
                nodes[firstFree] = new Node(data, -1, -1);
                currentNode.setLeft(firstFree);
                firstFree++;
            } else {
                insertRecursive(data, currentNode.getLeft());
            }
        } else {
            if (currentNode.getRight() == -1) {
                nodes[firstFree] = new Node(data, -1, -1);
                currentNode.setRight(firstFree);
                firstFree++;
            } else {
                insertRecursive(data, currentNode.getRight());
            }
        }
    }

    public void inOrder() {
        inOrderRecursive(root);
    }

    private void inOrderRecursive(int currentIndex) {
        if (currentIndex != -1) {
            Node currentNode = nodes[currentIndex];
            inOrderRecursive(currentNode.getLeft());
            System.out.print(currentNode.getData() + " ");
            inOrderRecursive(currentNode.getRight());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("root = ").append(root).append("\n");
        sb.append("firstFree = ").append(firstFree).append("\n");

        for (int i = 0; i < firstFree; i++) {
            Node currentNode = nodes[i];
            sb.append(i).append(": ").append(currentNode.getData()).append(", ")
                    .append(currentNode.getLeft()).append(", ").append(currentNode.getRight()).append("\n");
        }

        return sb.toString();
    }
}