package com.bytetrend.sandbox.util;

import java.util.*;

public class SampleHashMap<K, V> implements Map<K, V> {
    private static int DEFAULT_CAPACITY = 64;
    private Entry<K, V>[] table;   //Main bucket
    private int capacity;  //Initial capacity of HashMap
    private int size = 0;

    public SampleHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public SampleHashMap(int capacity) {
        this.capacity = capacity;
        table = new Entry[this.capacity];
    }

    /**
     * Maps a key hash to an index in the main bucket table.
     *
     * @param key
     * @return
     */
    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        boolean result = false;
        if (size > 0) {
            for(int i = 0; i < capacity && result == false; i++) {
                Entry<K, V> entry = table[i];
                while (entry != null) {
                    if(value.equals(entry.value)) {
                        result = true;
                        break;
                    }
                    entry = entry.next;
                }
            }
        }
        return result;
    }

    @Override
    public V get(Object o) {
        if (size == 0 || o == null || !(o instanceof Map.Entry))
            return null;
        Map.Entry<K, V> entry = (Map.Entry<K, V>) o;
        K key = entry.getKey();

        //calculate index in the table where object should be found.
        int hash = hash(key);
        Entry<K, V> current = table[hash];
        if (current != null) {
            Entry<K, V> previous = null;

            while (current != null) {
                if (current.key.equals(key)) {
                    return current.value;
                }
                previous = current;
                current = current.next;
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        //This implementation does not allow nulls
        if (key == null)
            return null;

        //calculate index in the table where object will be inserted.
        int hash = hash(key);

        Entry<K, V> newEntry = new Entry<K, V>(key, value, null);

        //Firs check is there is already a key with that hash
        Entry<K, V> current = table[hash];
        if (current == null) {
            table[hash] = newEntry;
            size++;
        } else {
            Entry<K, V> previous = null;
            //Find entry with a key that matches key
            while (current != null) {
                if (current.key.equals(key)) {
                    if (previous == null) {
                        //Found the same key in the first entry of this bucket
                        newEntry.next = current.next;
                        table[hash] = newEntry;
                    } else {
                        newEntry.next = current.next;
                        previous.next = newEntry;
                    }
                    size++;
                    //Return previous entry
                    return current.value;
                }
                previous = current;
                current = current.next;
            }
            size++;
            previous.next = newEntry;
        }

        return null;
    }

    @Override
    public V remove(Object o) {
        if (size == 0 || o == null || !(o instanceof Map.Entry))
            return null;
        Map.Entry<K, V> entry = (Map.Entry<K, V>) o;
        K key = entry.getKey();

        //calculate index in the table where object should be found.
        int hash = hash(key);
        Entry<K, V> current = table[hash];
        if (current != null) {
            Entry<K, V> previous = null;

            while (current != null) {
                if (current.key.equals(key)) {
                    if (previous == null) {
                        table[hash] = null;
                    } else {
                        previous.next = current.next;
                    }
                    size--;
                    return current.value;
                }
                previous = current;
                current = current.next;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
            put(e.getKey(), e.getValue());

    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> entrySet = new HashSet<>(size);
        for(int i = 0; i < capacity; i++) {
            Entry<K, V> entry = table[i];
            while (entry != null) {
                entrySet.add(entry.key);
                entry = entry.next;
            }
        }
        return  entrySet;
    }

    @Override
    public Collection<V> values() {
        Collection<V> entrySet = new ArrayList<>(size);
        for(int i = 0; i < capacity; i++) {
            Entry<K, V> entry = table[i];
            while (entry != null) {
                entrySet.add(entry.value);
                entry = entry.next;
            }
        }
        return  entrySet;
    }

    @Override
    public Set<Map.Entry<K,V>> entrySet() {
        Set<Map.Entry<K,V>> entrySet = new HashSet<>(size);
        for(int i = 0; i < capacity; i++) {
            Entry<K, V> entry = table[i];
            while (entry != null) {
                entrySet.add(entry);
                entry = entry.next;
            }
        }
        return  entrySet;
    }



    static class Entry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

    }
}