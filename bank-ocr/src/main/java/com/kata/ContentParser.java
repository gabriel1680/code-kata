package com.kata;

import java.util.*;

public class ContentParser {

    private static final String ZERO = " _ | ||_|   ";
    private static final String ONE = "     |  |   ";

    public List<Integer> parse(String content) {
        final var result = new ArrayList<Integer>();
        for (int i = 0; i < 27; i += 3) {
            result.add(parseOneNumberChunk(content, i));
        }
        return result;
    }

    public int parseOneNumberChunk(String content, int n) {
        final var numberChunks = new ArrayList<String>();
        for (String line : content.split("\n")) {
            numberChunks.add(line.substring(n, n + 3));
        }
        return numberChunksToInt(numberChunks);
    }

    private static int numberChunksToInt(List<String> list) {
        final var stringNumber = String.join("", list);
        return switch (stringNumber) {
            case ZERO -> 0;
            case ONE -> 1;
            default -> -1;
        };
    }
}
