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

    @Test
    void guessTheValidChecksum() {
        final var validChecksumDigits = List.of(3, 4, 5, 8, 8, 2, 8, 6, 5);
        var accountNumber = new AccountNumber(validChecksumDigits);
        assertThat(accountNumber.getPossibleCombinations()).containsExactly("345882865");

        var invalidChecksumDigits = List.of(5, 5, 5, 5, 5, 5, 5, 5, 5);
        accountNumber = new AccountNumber(invalidChecksumDigits);
        assertThat(accountNumber.getPossibleCombinations()).contains("555655555", "559555555");

        invalidChecksumDigits = List.of(6, 6, 6, 6, 6, 6, 6, 6, 6);
        accountNumber = new AccountNumber(invalidChecksumDigits);
        assertThat(accountNumber.getPossibleCombinations()).contains("666566666", "686666666");

        invalidChecksumDigits = List.of(8, 8, 8, 8, 8, 8, 8, 8, 8);
        accountNumber = new AccountNumber(invalidChecksumDigits);
        assertThat(accountNumber.getPossibleCombinations()).contains("888886888", "888888880", "888888988");

        invalidChecksumDigits = List.of(9, 9, 9, 9, 9, 9, 9, 9, 9);
        accountNumber = new AccountNumber(invalidChecksumDigits);
        assertThat(accountNumber.getPossibleCombinations()).contains("899999999", "993999999", "999959999");
    }
}