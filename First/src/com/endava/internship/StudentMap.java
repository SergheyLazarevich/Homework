package com.endava.internship;

import java.util.*;

public class StudentMap implements Map<Student, Integer> {
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private int size = 0;
    private Entry<Student, Integer>[] table;

    public StudentMap() {
        table = new Entry[INITIAL_CAPACITY];
    }

    static class Entry<K, V> implements Map.Entry<K, V> {
        final K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() { return key; }

        @Override
        public V getValue() { return value; }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

    private int getIndex(Object key) {
        return (key.hashCode() & 0x7FFFFFFF) % table.length;
    }

    private void resize() {
        Entry<Student, Integer>[] oldTable = table;
        table = new Entry[table.length * 2];
        size = 0;

        for (Entry<Student, Integer> entry : oldTable) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public boolean containsKey(Object key) {
        int index = getIndex(key);
        Entry<Student, Integer> entry = table[index];
        return entry != null && entry.key.equals(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (Entry<Student, Integer> entry : table) {
            if (entry != null && entry.value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer get(Object key) {
        int index = getIndex(key);
        Entry<Student, Integer> entry = table[index];
        return (entry != null && entry.key.equals(key)) ? entry.value : null;
    }

    @Override
    public Integer put(Student key, Integer value) {
        if (size >= table.length * LOAD_FACTOR) {
            resize();
        }

        int index = getIndex(key);
        while (table[index] != null && !table[index].key.equals(key)) {
            index = (index + 1) % table.length;
        }

        if (table[index] == null) {
            size++;
        }

        table[index] = new Entry<>(key, value);
        return value;
    }

    @Override
    public Integer remove(Object key) {
        int index = getIndex(key);
        while (table[index] != null) {
            if (table[index].key.equals(key)) {
                Integer oldValue = table[index].value;
                table[index] = null;
                size--;
                return oldValue;
            }
            index = (index + 1) % table.length;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends Student, ? extends Integer> m) {
        for (Map.Entry<? extends Student, ? extends Integer> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    @Override
    public Set<Student> keySet() {
        Set<Student> set = new HashSet<>();
        for (Entry<Student, Integer> entry : table) {
            if (entry != null) {
                set.add(entry.key);
            }
        }
        return set;
    }

    @Override
    public Collection<Integer> values() {
        Set<Integer> set = new HashSet<>();
        for (Entry<Student, Integer> entry : table) {
            if (entry != null) {
                set.add(entry.value);
            }
        }
        return set;
    }

    @Override
    public Set<Map.Entry<Student, Integer>> entrySet() {
        throw new UnsupportedOperationException();
    }
}
