package com.kata;

import java.util.ArrayList;
import java.util.List;

public class OCRFileParser {

    private final EntryParser entryParser = new EntryParser();

    public List<AccountNumber> parse(String content) {
        List<AccountNumber> accountNumbers = new ArrayList<>();
        List<String> entryLines = new ArrayList<>();
        int linesCounter = 0;
        for (var line : content.split("\n")) {
            entryLines.add(line);
            linesCounter++;
            if (linesCounter == 4) {
                String entry = String.join("\n", entryLines);
                accountNumbers.add(entryParser.parse(entry));
                linesCounter = 0;
                entryLines = new ArrayList<>();
            }
        }
        return accountNumbers;
    }
}
