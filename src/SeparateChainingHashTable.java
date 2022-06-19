import javax.swing.text.html.HTMLDocument;

public class SeparateChainingHashTable<Key, Value> {

    private static final int INIT_CAPACITY = 4;

    private int n;
    private int m;
    private Chain<Key, Value>[] symbolTable;

    public SeparateChainingHashTable() {
        this(INIT_CAPACITY);
    }

    public SeparateChainingHashTable(int m) {
        this.m = m;

        symbolTable = (Chain<Key, Value>[]) new Chain[m];
        for (int i = 0; i < symbolTable.length; i++) {
            symbolTable[i] = new Chain<Key, Value>();
        }
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Cannot get empty key");

        int i = hash(key);
        return symbolTable[i].get(key);
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("Cannot put empty key");

        if (value == null) {
            delete(key);
            return;
        }

        if (n >= 10 * m) resize(2 * n);

        int i = hash(key);
        if (!symbolTable[i].contains(key)) n++;
        symbolTable[i].put(key, value);
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("Null key cannot be deleted");

        int i = hash(key);
        if (symbolTable[i].contains(key)) n--;
        symbolTable[i].delete(key);

        if (m > INIT_CAPACITY && n <= m * 2) resize(m / 2);
    }

    private int hash(Key key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (m-1);
    }

    private void resize(int chains) {
        SeparateChainingHashTable<Key, Value> temp = new SeparateChainingHashTable<>(chains);
        for (int i = 0; i < m; i++) {
            for (Key key : symbolTable[i].keys()) {
                temp.put(key, symbolTable[i].get(key));
            }
        }

        this.m  = temp.m;
        this.n  = temp.n;
        this.symbolTable = temp.symbolTable;
    }

    public static void main(String[] args) {
        SeparateChainingHashTable<String, String> hashTable = new SeparateChainingHashTable();
        hashTable.put("Kosova", "Prishtina");
        hashTable.put("Shqipera", "Tirane");
        hashTable.put("Deutschland", "Berlin");
        System.out.println(hashTable.get("Kosova"));
        System.out.println(hashTable.get("Shqipera"));
        System.out.println(hashTable.get("Deutschland"));
    }
}
