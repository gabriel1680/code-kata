package com.kata;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Console {

    private final PrintStream out;
    private final Scanner scanner;

    public Console(InputStream in, PrintStream out) {
        this.out = out;
        this.scanner = new Scanner(in);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public void printLine(String s) {
        out.println(s);
    }
}
