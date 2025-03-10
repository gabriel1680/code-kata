package com.kata.out;

import com.kata.app.Message;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class InMemoryEmailServiceTest {
    @Test
    void shouldSendEmail() {
        final var out = new ByteArrayOutputStream();
        final var sut = new InMemoryEmailService(new PrintStream(out));
        final var message = new Message("a", "b");
        sut.send(message);
        assertThat(out.toString(), is(message + "\n"));
    }
}