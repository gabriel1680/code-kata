package com.kata;

public class Stack {

    private final int capacity;
    private final int[] elements;
    private int size;

    public Stack(int capacity) {
        if (capacity < 0)
            throw new InvalidCapacity();
        this.capacity = capacity;
        this.elements = new int[capacity];
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void push(int i) {
        if (size == capacity)
            throw new OverflowException();
        elements[size++] = i;
    }

    public int pop() {
        if (isEmpty())
            throw new UnderflowException();
        return elements[--size];
    }

    public int peek() {
        if (isEmpty())
            throw new UnderflowException();
        return elements[size - 1];
    }
}
