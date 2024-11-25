package com.kata.ocr;

import java.util.Arrays;

public enum OCRDigit {
    NEGATIVE("", -1),
    ZERO(" _ | ||_|   ", 0),
    ONE("     |  |   ", 1),
    TWO(" _  _||_    ", 2),
    THREE(" _  _| _|   ", 3),
    FOUR("   |_|  |   ", 4),
    FIVE(" _ |_  _|   ", 5),
    SIX(" _ |_ |_|   ", 6),
    SEVEN(" _   |  |   ", 7),
    EIGHT(" _ |_||_|   ", 8),
    NINE(" _ |_| _|   ", 9);

    public final int value;
    public final String representation;

    OCRDigit(String representation, int value) {
        this.value = value;
        this.representation = representation;
    }

    public static OCRDigit of(String s) {
        return Arrays.stream(OCRDigit.values())
                .filter(digit -> digit.representation.equals(s))
                .findFirst()
                .orElse(NEGATIVE);
    }
}