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
    private FileReader fileReader;

    @Mock
    private AccountNumberPresenter presenter;

    @Mock
    private IO io;

    @InjectMocks
    private BankOCR ocr;

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
        when(presenter.present(any())).thenReturn("123456789");
        ocr.run();
        verify(io, times(1)).print("123456789");
    }
}