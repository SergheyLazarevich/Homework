package com.endava.internship;

import java.util.LinkedList;
import java.util.Map;

public class StudentMap implements Map<Student, Integer> {

    private static final int INITIAL_CAPACITY = 16;
    private LinkedList<Entry<Student, Integer>>[] table;

    public StudentMap() {
        table = new LinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            table[i] = new LinkedList<>();
        }
    }

    static class Entry<K,V> {
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
        int size = 0;
        for (LinkedList<Entry<Student, Integer>> list : table) {
                size += list.size();

        }
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
            if(entry.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (LinkedList<Entry<Student, Integer>> list : table) {
            for (Entry<Student, Integer> entry : list) {
                if(entry.value.equals(value)) {
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
            if(entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }
}
