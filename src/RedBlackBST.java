public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static boolean RED = true;
    private static boolean BLACK = false;

    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
        int size;

        public Node(Key key, Value value, boolean color, int size) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node h, Key key, Value value) {
        if (h == null) return new Node(key, value, RED, 1);

        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.left = put(h.left, key, value);
        else if (cmp > 0) h.right = put(h.right, key, value);
        else              h.value = value;

        if (isRed(h.right) && !isRed(h.left))     h = rotateLeft(h);
        if (isRed(h.left)  && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left)  && isRed(h.right))     flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }

    private boolean isRed(Node h) {
        return h.color;
    }

    private int size(Node h) {
        if (h == null) return 0;
        return h.size;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;

        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;

        return x;
    }

    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    public static void main(String[] args) {
    }
}
