package com.kata;

public class EntryInterpreter {
    public int execute(String entry) {
        if ("\n|\n|\n".equals(entry)) return 1;
        else if ("_\n_|\n|_\n".equals(entry)) return 2;
        else if ("_\n_|\n_|\n".equals(entry)) return 3;
        else if ("\n|_|\n|\n".equals(entry)) return 4;
        else if ("_\n|_\n_|\n".equals(entry)) return 5;
        else if ("_\n|_\n|_|\n".equals(entry)) return 6;
        else if ("_\n|\n|\n".equals(entry)) return 7;
        else if ("_\n|_|\n|_|\n".equals(entry)) return 8;
        else if ("_\n|_|\n_|\n".equals(entry)) return 9;
        else return 0;
    }
}
