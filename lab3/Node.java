public class Node {
    private String data;
    private int left;
    private int right;

    public Node(String data, int left, int right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public void setData(String newData) {
        this.data = newData;
    }

    public String getData() {
        return data;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }
}