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
        return translate(cypher, key, ALPHABET);
    }

    public String encrypt(String word) {
        return translate(word, ALPHABET, key);
    }

    private static String translate(String word, char[] source, char[] from) {
        StringBuilder result = new StringBuilder();
        for (char c : word.toCharArray()) {
            result.append(getChar(c, source, from));
        }
        return result.toString();
    }

    private static String getChar(char c, char[] source, char[] from) {
        final var index = indexOf(c, source);
        if (index == -1)
            throw new IllegalArgumentException("invalid token");
        return String.valueOf(from[index]);
    }

    private static int indexOf(char c, char[] source) {
        for (int i = 0; i < source.length; i++) {
            if (source[i] == c) return i;
        }
        return -1;
    }
}
