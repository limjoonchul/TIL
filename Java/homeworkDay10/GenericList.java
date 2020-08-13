package com.company;

interface List<T> {
    void append(T value);
    void prepend(T value);
    void insert(int index, T value);
    void remove(int index);
    T get(int index);
    int length();
}

class ArrayList<T> implements List<T>{
    private int capacity;
    private int length;
    private Object[] integers;

    public ArrayList(int capacity) {
        this.capacity = capacity;
        length = 0;
        integers = new Object[capacity];
    }

    private void expandCapacity(int offset) {
        Object [] newIntegers = new Object[capacity * 2];
        System.arraycopy(integers, 0, newIntegers, offset, capacity);
        integers = newIntegers;
        capacity *= 2;
    }

    private void expandCapacity() {
        expandCapacity(0);
    }

    @Override
    public void append(T value) {
        if(length == capacity){
            expandCapacity();
        }
        integers[length++] = value;
        
    }

    @Override
    public void prepend(T value) {
        if (length == capacity) {
            expandCapacity(1);
        } else {
            if (length >= 0) System.arraycopy(integers, 0, integers, 1, length);
        }
        integers[0] =  value;
        length++;
    }

    @Override
    public void insert(int index, T value) {
        if (length == capacity) {
            expandCapacity();
        }
        if (index <= length && length >= 0) {
            System.arraycopy(integers, index, integers, index + 1, length - index);
        }
        integers[index] =  value;
        length++;
    }

    @Override
    public void remove(int index) {
        if (index < length && length >= 0) {
            System.arraycopy(integers, index + 1, integers, index, length - index - 1);
        }
        length--;

    }

    @Override
    public T get(int index) {
        if (index < length) {
            return (T) integers[index];
        } else {
            return (T) Integer.valueOf(-1);
        }
    }

    @Override
    public int length() {
        return length;
    }
}

public class GenericList {
    public static void printList(ArrayList<Integer> list) {
        for (int i = 0; i < list.length(); i++) {
            System.out.printf("%d ", list.get(i));
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>(10);
        for (int i = 0; i < 20; i++) {
            list.append(i);
        }
        printList(list);

        list.remove(5);
        printList(list);

        list.prepend(1);
        list.prepend(2);
        list.prepend(3);
        printList(list);

        list.insert(5, 100);
        printList(list);
    }
}
