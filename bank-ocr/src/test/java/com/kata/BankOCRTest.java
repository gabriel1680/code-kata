package com.kata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankOCRTest {

    @Mock
    private IO io;

    @Mock
    private FileReader fileReader;

    @InjectMocks
    private BankOCR ocr;

    @Test
    void givenAFileWithOneEntry_whenReadIt_thenShouldBeAbleToParseItIntoAAccountNumber() {
        final var filepath = "/home/some-filepath.txt";
        final var content = """
                    _  _     _  _  _  _  _\s
                  | _| _||_||_ |_   ||_||_|
                  ||_  _|  | _||_|  ||_| _|
                                          \s
                    _  _     _  _  _  _  _\s
                  | _| _||_||_ |_   ||_||_|
                  ||_  _|  | _||_|  ||_||_|
                                          \s
                    _  _     _  _  _  _  _\s
                  | _| _||_||_ |_   || ||_|
                  ||_  _|  | _||_|  || ||_|
                                          \s
                """;
        when(io.read()).thenReturn(filepath);
        when(fileReader.read(filepath)).thenReturn(content);
        ocr.run();
        verify(io, times(1)).print("123456789");
        verify(io, times(1)).print("123456788 ERR");
        verify(io, times(1)).print("1234567?8 ILL");
    }
}