package com.kata;

import java.util.stream.Collectors;

public class AccountNumberPresenter {

    public String present(AccountNumber accountNumber) {
        final var digits = accountNumber.digits().stream()
                .map(String::valueOf)
                .map(AccountNumberPresenter::replaceInvalidDigits)
                .collect(Collectors.joining());
        return digits + getStatus(digits, accountNumber.checksum());
    }

    private static String replaceInvalidDigits(String digit) {
        return digit.equals("-1") ? "?" : digit;
    }

    private static String getStatus(String digits, boolean checksum) {
        if (digits.contains("?")) {
            return " ILL";
        } else if (!checksum) {
            return " ERR";
        } else {
            return "";
        }
    }
}
