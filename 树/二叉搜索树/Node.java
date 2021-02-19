package binary_search_tree;

public class Node {
    public Integer key;
    Node left;
    Node right;

    public Node(Integer key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                '}';
    }
}
