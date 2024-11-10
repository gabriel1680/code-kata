package com.kata;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AccountNumberTest {

    @Test
    void checksumForValidAccountNumber() {
        final var digits = List.of(3, 4, 5, 8, 8, 2, 8, 6, 5);
        final var accountNumber = new AccountNumber(digits);
        assertThat(accountNumber.checksum()).isTrue();
    }

    @Test
    void checksumForInvalidAccountNumber() {
        final var digits = List.of(6, 6, 4, 3, 7, 1, 4, 9, 5);
        final var accountNumber = new AccountNumber(digits);
        assertThat(accountNumber.checksum()).isFalse();
    }
}