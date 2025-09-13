import java.util.Objects;

public class MyHashMap<K, V> {
    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    private Node<K, V>[] table;
    private int size;
    private int capacity;
    private final float loadFactor;

    public MyHashMap() {
        this.capacity = 16;
        this.loadFactor = 0.75f;
        this.table = new Node[capacity];
    }

    private int hash(Object key) {
        if (key == null) return 0;
        int h = key.hashCode();
        h ^= (h >>> 16);
        return h & (capacity - 1);
    }

    public void put(K key, V value) {
        if (size >= capacity * loadFactor) {
            resize();
        }
        int index = hash(key);
        Node<K, V> head = table[index];

        for (Node<K, V> e = head; e != null; e = e.next) {
            if (Objects.equals(e.key, key)) {
                e.value = value;
                return;
            }
        }
        Node<K, V> newEntry = new Node<>(key, value, head);
        table[index] = newEntry;
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        for (Node<K, V> e = table[index]; e != null; e = e.next) {
            if (Objects.equals(e.key, key)) {
                return e.value;
            }
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        Node<K, V> prev = null;
        Node<K, V> curr = table[index];

        while (curr != null) {
            if (Objects.equals(curr.key, key)) {
                if (prev == null) {
                    table[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                size--;
                return curr.value;
            }
            prev = curr;
            curr = curr.next;
        }
        return null;
    }

    private void resize() {
        capacity *= 2;
        Node<K, V>[] oldTable = table;
        table = new Node[capacity];
        size = 0;

        for (Node<K, V> head : oldTable) {
            for (Node<K, V> e = head; e != null; e = e.next) {
                put(e.key, e.value);
            }
        }
    }

    public void printAll() {
        for (int i = 0; i < capacity; i++) {
            Node<K, V> e = table[i];
            while (e != null) {
                System.out.println(e);
                e = e.next;
            }
        }
    }

    public int size() {
        return size;
    }

}
