package com.kata;

import java.util.Arrays;

public enum Operation {
    SUM('+', Integer::sum),
    MULTIPLY('*', (a, b) -> a * b);

    @FunctionalInterface
    private interface OperationExecutor {
        int execute(int a, int b);
    }

    public final char value;
    private final OperationExecutor executor;

    Operation(char c, OperationExecutor executor) {
        value = c;
        this.executor = executor;
    }

    public static Operation of(char c) {
        return Arrays.stream(Operation.values()).filter(v -> v.value == c).findFirst().orElseThrow();
    }

    public static boolean isOperation(char c) {
        return Arrays.stream(Operation.values()).anyMatch(v -> v.value == c);
    }

    public int execute(int a, int b) {
        return executor.execute(a, b);
    }
}
