package com.kata;

public class EntryInterpreter {
    public int execute(String entry) {
        return Entry.of(entry).value;
    }
}
