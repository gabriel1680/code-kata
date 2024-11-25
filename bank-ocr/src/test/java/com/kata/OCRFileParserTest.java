package com.kata;

import com.kata.ocr.OCRFileParser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OCRFileParserTest {

    @Test
    void parseFile() {
        final var content = """
                    _  _     _  _  _  _  _\s
                  | _| _||_||_ |_   ||_||_|
                  ||_  _|  | _||_|  ||_| _|
                                          \s
                    _  _     _  _  _  _  _\s
                  | _||_||_||_ |_|  ||_||_|
                  ||_  _|  | _||_|  ||_| _|
                                          \s
                """;
        final var ocrFileParser = new OCRFileParser();
        final var accountNumbers = ocrFileParser.parse(content);
        assertThat(accountNumbers).size().isEqualTo(2);
        assertThat(accountNumbers.get(0).digits()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertThat(accountNumbers.get(1).digits()).containsExactly(1, 2, 9, 4, 5, 8, 7, 8, 9);
    }
}