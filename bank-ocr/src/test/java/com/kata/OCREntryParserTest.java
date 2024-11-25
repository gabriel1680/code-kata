package com.kata;

import com.kata.ocr.OCREntryParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


class OCREntryParserTest {

    @ParameterizedTest
    @MethodSource("provider")
    void shouldParseAnOCREntry(String entry, List<Integer> digits) {
        final var entryParser = new OCREntryParser();
        final var accountNumber = entryParser.parse(entry);
        assertThat(accountNumber.digits()).isEqualTo(digits);
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(
                        """
                                 _  _  _  _  _  _  _  _  _\s
                                | || || || || || || || || |
                                |_||_||_||_||_||_||_||_||_|
                                                          \s
                                """,
                        List.of(0, 0, 0, 0, 0, 0, 0, 0, 0)
                ),
                Arguments.of(
                        """
                                                          \s
                                  |  |  |  |  |  |  |  |  |
                                  |  |  |  |  |  |  |  |  |
                                                          \s
                                """,
                        List.of(1, 1, 1, 1, 1, 1, 1, 1, 1)
                ),
                Arguments.of(
                        """
                                 _  _  _  _  _  _  _  _  _\s
                                 _| _| _| _| _| _| _| _| _|
                                |_ |_ |_ |_ |_ |_ |_ |_ |_\s
                                                          \s
                                """,
                        List.of(2, 2, 2, 2, 2, 2, 2, 2, 2)
                ),
                Arguments.of(
                        """
                                 _  _  _  _  _  _  _  _  _\s
                                 _| _| _| _| _| _| _| _| _|
                                 _| _| _| _| _| _| _| _| _|
                                                           \s
                                """,
                        List.of(3, 3, 3, 3, 3, 3, 3, 3, 3)
                ),
                Arguments.of(
                        """
                                                          \s
                                |_||_||_||_||_||_||_||_||_|
                                  |  |  |  |  |  |  |  |  |
                                                          \s
                                """,
                        List.of(4, 4, 4, 4, 4, 4, 4, 4, 4)
                ),
                Arguments.of(
                        """
                                 _  _  _  _  _  _  _  _  _\s
                                |_ |_ |_ |_ |_ |_ |_ |_ |_\s
                                 _| _| _| _| _| _| _| _| _|
                                                          \s
                                """,
                        List.of(5, 5, 5, 5, 5, 5, 5, 5, 5)
                ),
                Arguments.of(
                        """
                                    _  _     _  _  _  _  _\s
                                  | _| _||_||_ |_   ||_||_|
                                  ||_  _|  | _||_|  ||_| _|
                                                          \s
                                """,
                        List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                )
        );
    }
}