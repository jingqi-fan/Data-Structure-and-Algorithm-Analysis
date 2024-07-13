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

    public boolean remove(String data) {
        if (root == -1) {
            return false; // Tree is empty
        }
        return removeRecursive(data, root);
    }

    private boolean removeRecursive(String data, int currentIndex) {
        Node currentNode = nodes[currentIndex];
        if (currentNode == null) {
            return false; // Data not found in tree
        }

        int comparison = data.compareTo(currentNode.getData());
        if (comparison == 0) {
            // Case 1: Node has no children
            if (currentNode.getLeft() == -1 && currentNode.getRight() == -1) {
                nodes[currentIndex] = null;
            }
            // Case 2: Node has only left child
            else if (currentNode.getLeft() != -1 && currentNode.getRight() == -1) {
                nodes[currentIndex] = nodes[currentNode.getLeft()];
            }
            // Case 3: Node has only right child
            else if (currentNode.getLeft() == -1 && currentNode.getRight() != -1) {
                nodes[currentIndex] = nodes[currentNode.getRight()];
            }
            // Case 4: Node has two children
            else {
                int successorIndex = findSuccessor(currentNode.getRight());
                Node successorNode = nodes[successorIndex];
                currentNode.setData(successorNode.getData());
                removeRecursive(successorNode.getData(), currentNode.getRight());
            }
            return true;
        } else if (comparison < 0) {
            return removeRecursive(data, currentNode.getLeft());
        } else {
            return removeRecursive(data, currentNode.getRight());
        }
    }

    private int findSuccessor(int currentIndex) {
        Node currentNode = nodes[currentIndex];
        while (currentNode.getLeft() != -1) {
            currentIndex = currentNode.getLeft();
            currentNode = nodes[currentIndex];
        }
        return currentIndex;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("root = ").append(root).append("\n");
        sb.append("firstFree = ").append(firstFree).append("\n");

        for (int i = 0; i < firstFree; i++) {
            Node currentNode = nodes[i];
            if (currentNode != null) {
                sb.append(i).append(": ").append(currentNode.getData()).append(", ")
                        .append(currentNode.getLeft()).append(", ").append(currentNode.getRight()).append("\n");
            }else {
                sb.append(i).append(": null\n");
            }
        }

        return sb.toString();
    }
}