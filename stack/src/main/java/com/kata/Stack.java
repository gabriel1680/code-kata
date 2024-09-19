package com.kata;

public class Stack {

    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void push(int i) {
        size++;
    }
}
