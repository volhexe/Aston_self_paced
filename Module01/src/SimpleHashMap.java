class SimpleHashMap<K, V> {
    private Object[] keys;
    private Object[] values;
    private int capacity = 16;
    private final float loadFactor = 0.75f;
    private int size = 0;

    public SimpleHashMap() {
        keys = new Object[capacity];
        values = new Object[capacity];
    }

    private int getIndex(Object key) {
        if (key == null) return 0;
        return Math.abs(key.hashCode()) % capacity;
    }

    public V get(K key) {
        int index = getIndex(key);
        if (keys[index] != null && keys[index].equals(key)) {
            return (V) values[index];
        }
        return null;
    }

    public void put(K key, V value) {
        if (size >= capacity * loadFactor) {
            resize();
        }
        int index = getIndex(key);
        if (keys[index] == null) {
            size++;
        }
        keys[index] = key;
        values[index] = value;
    }

    public void remove(K key) {
        int index = getIndex(key);
        if (keys[index] != null && keys[index].equals(key)) {
            keys[index] = null;
            values[index] = null;
            size--;
        }
    }

    private void resize() {
        int oldCapacity = capacity;
        capacity *= 2;
        Object[] oldKeys = keys;
        Object[] oldValues = values;

        keys = new Object[capacity];
        values = new Object[capacity];
        size = 0;

        for (int i = 0; i < oldCapacity; i++) {
            if (oldKeys[i] != null) {
                put((K) oldKeys[i], (V) oldValues[i]);
            }
        }
    }

    public void printArray() {
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null) {
            System.out.println(i + ": " + keys[i] + " -> " + values[i]);}
        }
    }

    public int size() {
        return size;
    }
}


