package com.kata;

import java.io.PrintStream;
import java.util.Scanner;

public class Console implements IODevice {

    private final PrintStream out;
    private final Scanner scanner;

    public Console(PrintStream out, Scanner scanner) {
        this.out = out;
        this.scanner = scanner;
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public void printLine(String s) {
        out.println(s);
    }
}
