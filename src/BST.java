public class BST {

    private Node root;

    private class Node {
        String key;
        Integer value;
        Node left;
        Node right;
        int size;

        public Node(String key, Integer value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public Integer get(String key) {
        return get(root, key);
    }

    private Integer get(Node x, String key) {
        if (key == null) throw new IllegalArgumentException("Cannot get a null key");
        if (x == null)   return null;

        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.value;
    }

    public void put(String key, Integer value) {
        root = put(root, key, value);
    }

    private Node put(Node x, String key, Integer value) {
        if (x == null) return new Node(key, value, 1);

        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else              x.value = value;

        x.size = size(x.left) + size(x.right) + 1;

        return x;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.put("S", 2);
        bst.put("B", 4);
        bst.put("C", 1);

        System.out.printf("get(%s) = %d\n", "S", bst.get("S"));
        System.out.printf("get(%s) = %d\n", "B", bst.get("B"));
        System.out.printf("get(%s) = %d\n", "C", bst.get("C"));
    }
}
