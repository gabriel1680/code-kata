package com.kata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankOCRTest {

    @Mock
    private FileReader fileReader;

    @Mock
    private IO io;

    @Test
    void givenAFileWithOneEntry_whenReadIt_thenShouldBeAbleToParseItIntoAAccountNumber() {
        final var file = "some-file.txt";
        final var content = """
                    _  _     _  _  _  _  _\s
                  | _| _||_||_ |_   ||_||_|
                  ||_  _|  | _||_|  ||_| _|
                                          \s
                """;
        when(fileReader.read(file)).thenReturn(content);
        when(io.read()).thenReturn(file);
        final var ocr = new BankOCR(fileReader, io);
        ocr.run();
        verify(io, times(1)).print("123456789");
    }
}