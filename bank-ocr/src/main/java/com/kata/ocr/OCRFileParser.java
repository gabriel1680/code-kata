package com.kata.ocr;

import com.kata.AccountNumber;

import java.util.List;
import java.util.stream.IntStream;

public class OCRFileParser {

    private final static int ENTRY_SIZE = 112;

    private final OCREntryParser entryParser = new OCREntryParser();

    public List<AccountNumber> parse(String content) {
        return IntStream.iterate(0, i -> i + ENTRY_SIZE)
                .limit((content.length() / ENTRY_SIZE))
                .mapToObj(i -> content.substring(i, i + ENTRY_SIZE))
                .map(entryParser::parse)
                .toList();
    }
}
