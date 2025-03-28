package com.endava.internship;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class StudentMap implements Map<Student, Integer> {

    private static final int INITIAL_CAPACITY = 16;
    private LinkedList<Entry<Student, Integer>>[] table;
    private static final float LOAD_FACTOR = 0.75f;

    private int size = 0;

    public StudentMap() {
        table = new LinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            table[i] = new LinkedList<>();
        }
    }

    static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int getIndex(Student key) {
        return key.hashCode() % table.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int index = getIndex((Student) key);
        LinkedList<Entry<Student, Integer>> list = table[index];
        for (Entry<Student, Integer> entry : list) {
            if (entry.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (LinkedList<Entry<Student, Integer>> list : table) {
            for (Entry<Student, Integer> entry : list) {
                if (entry.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public Integer get(Object key) {
        int index = getIndex((Student) key);
        LinkedList<Entry<Student, Integer>> list = table[index];
        for (Entry<Student, Integer> entry : list) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    private void resize() {
        int newCapacity = table.length * 2;
        LinkedList<Entry<Student, Integer>>[] newTable = new LinkedList[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newTable[i] = new LinkedList<>();
        }

        for (LinkedList<Entry<Student, Integer>> list : table) {
            for (Entry<Student, Integer> entry : list) {
                int newIndex = entry.key.hashCode() % newCapacity;
                newTable[newIndex].add(entry);
            }
        }
        table = newTable;

    }

    @Override
    public Integer put(Student key, Integer value) {

        if (size >= table.length * LOAD_FACTOR) {
            resize();
        }

        int index = getIndex(key);
        LinkedList<Entry<Student, Integer>> list = table[index];
        for (Entry<Student, Integer> entry : list) {
            if (entry.key.equals(key)) {
                Integer oldValue = entry.value;
                entry.value = value;
                return oldValue;

            }
        }
        list.add(new Entry(key, value));
        size++;
        return null;
    }

    @Override
    public Integer remove(Object key) {
        int index = getIndex((Student) key);
        LinkedList<Entry<Student, Integer>> list = table[index];
        for (Iterator<Entry<Student, Integer>> iterator = list.iterator(); iterator.hasNext(); ) {
            Entry<Student, Integer> entry = iterator.next();
            if (entry.key.equals(key)) {
                iterator.remove();
                size--;
                return entry.value;
            }
        }
        return null;

    }


    @Override
    public void putAll(Map<? extends Student, ? extends Integer> m) {
        for (Map.Entry<? extends Student, ? extends Integer> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

   


}
