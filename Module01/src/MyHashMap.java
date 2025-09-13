import java.util.Objects;

public class MyHashMap<K, V> {
    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    private Entry<K, V>[] table;
    private int size;
    private int capacity;
    private final float loadFactor;

    public MyHashMap() {
        this.capacity = 16;
        this.loadFactor = 0.75f;
        this.table = new Entry[capacity];
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
        Entry<K, V> head = table[index];

        for (Entry<K, V> e = head; e != null; e = e.next) {
            if (Objects.equals(e.key, key)) {
                e.value = value;
                return;
            }
        }
        Entry<K, V> newEntry = new Entry<>(key, value, head);
        table[index] = newEntry;
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            if (Objects.equals(e.key, key)) {
                return e.value;
            }
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        Entry<K, V> prev = null;
        Entry<K, V> curr = table[index];

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
        Entry<K, V>[] oldTable = table;
        table = new Entry[capacity];
        size = 0;

        for (Entry<K, V> head : oldTable) {
            for (Entry<K, V> e = head; e != null; e = e.next) {
                put(e.key, e.value);
            }
        }
    }

    public void printAll() {
        for (int i = 0; i < capacity; i++) {
            Entry<K, V> e = table[i];
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
