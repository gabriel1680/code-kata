package com.kata;

import java.util.*;

public class ContentParser {

    private static final String ZERO = " _ | ||_|   ";
    private static final String ONE = "     |  |   ";
    private static final String TWO = " _  _||_    ";

    public List<Integer> parse(String content) {
        final var result = new ArrayList<Integer>();
        final List<String> split = List.of(content.split("\n"));
        for (int i = 0; i < 27; i += 3) {
            result.add(parseOneNumberChunk(split, i));
        }
        return result;
    }

    public int parseOneNumberChunk(List<String> lines, int n) {
        final var numberChunks = new ArrayList<String>();
        for (String line : lines) {
            numberChunks.add(line.substring(n, n + 3));
        }
        return numberChunksToInt(numberChunks);
    }

    private static int numberChunksToInt(List<String> list) {
        final var stringNumber = String.join("", list);
        return switch (stringNumber) {
            case ZERO -> 0;
            case ONE -> 1;
            case TWO -> 2;
            default -> -1;
        };
    }
}
