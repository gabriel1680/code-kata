package com.kata;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.kata.Digit.*;
import static org.assertj.core.api.Assertions.assertThat;

class AccountNumberTest {

    @Test
    void checksumForValidAccountNumber() {
        final var digits = List.of(THREE, FOUR, FIVE, EIGHT, EIGHT, TWO, EIGHT, SIX, FIVE);
        final var accountNumber = new AccountNumber(digits);
        assertThat(accountNumber.checksum()).isTrue();
    }

    @Test
    void checksumForInvalidAccountNumber() {
        final var digits = List.of(ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE);
        final var accountNumber = new AccountNumber(digits);
        assertThat(accountNumber.checksum()).isFalse();
    }
}