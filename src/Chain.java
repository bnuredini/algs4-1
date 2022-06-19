import java.util.ArrayList;
import java.util.List;

public class Chain<Key, Value> {

    private int n;
    private Node first;

    private class Node {
        private Key key;
        private Value value;
        private Node next;

        public Node() {}

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public Chain() {}

    public Chain(int n) {
        this.n = n;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Cannot get empty key");

        for (Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)) {
                return x.value;
            }
        }

        return null;
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("Cannot put empty key");

        if (value == null) {
            delete(key);
            return;
        }

        for (Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)) {
                x.value = value;
                return;
            }
        }

        first = new Node(key, value, first);
        n++;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("Null key cannot be deleted");
        delete(first, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;

        if (x.key.equals(key)) {
            n--;
            return x.next;
        }

        x.next = delete(x.next, key);
        return x;
    }

    public boolean contains(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)) {
                return true;
            }
        }

        return false;
    }

    public List<Key> keys() {
        List<Key> list = new ArrayList<>();
        for (Node x = first ; x != null; x = x.next) {
            list.add(x.key);
        }

        return list;
    }

    public void print() {
        for (Node x = first; x != null; x = x.next) {
            System.out.print(x.key +  " ");
        }
    }

    private boolean isEmpty() {
        return n == 0;
    }

    public static void main(String[] args) {
        Chain<Integer, Integer> list = new Chain<>();
        list.put(1, 1);
        list.put(2, 2);
        list.put(3, 3);
        list.put(4, 4);
        list.delete(1);
        list.print();
    }
}
