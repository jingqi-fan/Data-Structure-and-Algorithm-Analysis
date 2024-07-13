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
            return false; // The tree is empty
        }
        int nodeIndex = findNodeIndex(data, root);
        if (nodeIndex == -1) {
            return false; // Data not found
        }
        removeRecursive(nodeIndex);
        return true;
    }

    private int findNodeIndex(String data, int currentIndex) {
        if (currentIndex == -1) {
            return -1; // Reached the end of the tree without finding the data
        }
        Node currentNode = nodes[currentIndex];
        if (currentNode.getData().equals(data)) {
            return currentIndex; // Found the data at the current index
        } else if (data.compareTo(currentNode.getData()) < 0) {
            return findNodeIndex(data, currentNode.getLeft()); // Search in the left subtree
        } else {
            return findNodeIndex(data, currentNode.getRight()); // Search in the right subtree
        }
    }

    private void removeRecursive(int currentIndex) {
        Node currentNode = nodes[currentIndex];
        if (currentNode.getLeft() == -1 && currentNode.getRight() == -1) {
            // Case 1: The node has no children
            nodes[currentIndex] = null;
            compactify(currentIndex);
        } else if (currentNode.getLeft() != -1 && currentNode.getRight() == -1) {
            // Case 2: The node has only a left child
            int leftChildIndex = currentNode.getLeft();
            nodes[currentIndex] = nodes[leftChildIndex];
            nodes[leftChildIndex] = null;
            compactify(leftChildIndex);
        } else if (currentNode.getLeft() == -1 && currentNode.getRight() != -1) {
            // Case 3: The node has only a right child
            int rightChildIndex = currentNode.getRight();
            nodes[currentIndex] = nodes[rightChildIndex];
            nodes[rightChildIndex] = null;
            compactify(rightChildIndex);
        } else {
            // Case 4: The node has both left and right children
            int successorIndex = findSuccessor(currentNode.getRight());
            Node successorNode = nodes[successorIndex];
            currentNode.setData(successorNode.getData());
            removeRecursive(successorIndex);
        }

        // Set the left and right indices of removed nodes to -1
        if (currentNode.getLeft() == -1 && currentNode.getRight() == -1) {
            if (currentIndex == root) {
                root = -1;
            } else {
                int parentIndex = findParent(currentIndex);
                Node parentNode = nodes[parentIndex];
                if (currentIndex == parentNode.getLeft()) {
                    parentNode.setLeft(-1);
                } else {
                    parentNode.setRight(-1);
                }
            }
        }
    }

    private int findParent(int currentIndex) {
        Node currentNode = nodes[currentIndex];
        int parentIndex = -1;
        int tempIndex = 0; // Start from the root of the tree
        while (tempIndex != currentIndex) {
            Node tempNode = nodes[tempIndex];
            int comparison = currentNode.getData().compareTo(tempNode.getData());
            if (comparison < 0) {
                parentIndex = tempIndex;
                tempIndex = tempNode.getLeft();
            } else {
                parentIndex = tempIndex;
                tempIndex = tempNode.getRight();
            }
        }
        return parentIndex;
    }

    private void compactify(int removedIndex) {
        int lastIndex = firstFree - 1;
        if (removedIndex != lastIndex) {
            nodes[removedIndex] = nodes[lastIndex];
            nodes[lastIndex] = null;
            updatePointers(removedIndex, lastIndex);
        }
        firstFree--;

        // Update the left and right indices of the nodes
        for (int i = 0; i < firstFree; i++) {
            Node currentNode = nodes[i];
            if (currentNode != null) {
                if (currentNode.getLeft() == lastIndex) {
                    currentNode.setLeft(removedIndex);
                }
                if (currentNode.getRight() == lastIndex) {
                    currentNode.setRight(removedIndex);
                }
            }
        }
    }

    private void updatePointers(int removedIndex, int lastIndex) {
        for (int i = 0; i < firstFree; i++) {
            Node currentNode = nodes[i];
            if (currentNode != null) {
                if (currentNode.getLeft() == lastIndex) {
                    currentNode.setLeft(removedIndex);
                }
                if (currentNode.getRight() == lastIndex) {
                    currentNode.setRight(removedIndex);
                }
            }
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
            } else {
                sb.append(i).append(": null\n");
            }
        }

        return sb.toString();
    }
}
