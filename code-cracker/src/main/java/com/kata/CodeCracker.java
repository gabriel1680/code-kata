package com.kata;

public class CodeCracker {

    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    private final char[] key;

    private CodeCracker(char[] keyList) {
        this.key = keyList;
    }

    public static CodeCracker of(String key) {
        char[] keyList = String.join("", key.split(" ")).toCharArray();
        return new CodeCracker(keyList);
    }

    public String decrypt(String cypher) {
        StringBuilder result = new StringBuilder();
        for (char c : cypher.toCharArray()) {
            result.append(getChar(c));
        }
        return result.toString();
    }

    private String getChar(char c) {
        final var index = indexOf(c);
        if (index == -1)
            throw new IllegalArgumentException("invalid token");
        return String.valueOf(ALPHABET[index]);
    }

    private int indexOf(char c) {
        for (int i = 0; i < key.length; i++) {
            if (key[i] == c) return i;
        }
        return -1;
    }
}
