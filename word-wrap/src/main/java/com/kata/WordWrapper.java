package com.kata;

public class WordWrapper {

    public String wrap(String word, int cols) {
        if (word.length() <= cols) {
            return word;
        }
        int br = word.lastIndexOf(" ", cols);
        if (br == -1) {
            br = cols;
        }
        return word.substring(0, br).trim() + "\n" + wrap(word.substring(br).trim(), cols);
    }
}
