package com.kata;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AccountNumberPresenterTest {

    @Test
    void presentValidAccountNumber() {
        final var digits = List.of(3, 4, 5, 8, 8, 2, 8, 6, 5);
        final var presenter = new AccountNumberPresenter();
        assertThat(presenter.present(new AccountNumber(digits)))
                .isEqualTo("345882865");
    }

    @Test
    void presentAInvalidAccountNumber() {
        final var digits = List.of(1, 1);
        final var presenter = new AccountNumberPresenter();
        assertThat(presenter.present(new AccountNumber(digits)))
                .isEqualTo("11 ILL");
    }

    @Test
    void presentAnAccountNumberWithIllegalCharacter() {
        final var digits = List.of(6, 6, 4, 3, 7, -1, 4, 9, 5);
        final var presenter = new AccountNumberPresenter();
        assertThat(presenter.present(new AccountNumber(digits)))
                .isEqualTo("66437?495 ILL");
    }

    @Test
    void presentAnAccountNumberWithInvalidChecksumButWithPossibleCombinations() {
        final var digits = List.of(8, 8, 8, 8, 8, 8, 8, 8, 8);
        final var presenter = new AccountNumberPresenter();
        assertThat(presenter.present(new AccountNumber(digits)))
                .isEqualTo("888888888 AMB [888886888, 888888880, 888888988]");
    }

    @Test
    void presentAnAccountNumberWithInvalidChecksumButWithOnePossibleCombination() {
        final var digits = List.of(3, 3, 3, 3, 3, 3, 3, 3, 3);
        final var presenter = new AccountNumberPresenter();
        assertThat(presenter.present(new AccountNumber(digits)))
                .isEqualTo("333393333");
    }
}