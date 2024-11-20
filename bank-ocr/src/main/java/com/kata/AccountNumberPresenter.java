package com.kata;

import java.util.List;
import java.util.stream.Collectors;

public class AccountNumberPresenter {

    public String present(AccountNumber accountNumber) {
        final var digits = accountNumber.digits().stream()
                .map(String::valueOf)
                .map(AccountNumberPresenter::replaceInvalidDigits)
                .collect(Collectors.joining());
        return present(
                digits,
                accountNumber.checksum(),
                accountNumber.getPossibleCombinations());
    }

    private static String replaceInvalidDigits(String digit) {
        return digit.equals("-1") ? "?" : digit;
    }

    private static String present(String digits, boolean checksum, List<String> possibleChecksum) {
        if (digits.contains("?")) {
            return digits + " ILL";
        }
        if (!checksum) {
            return presentForInvalidChecksum(digits, possibleChecksum);
        }
        return digits;
    }

    private static String presentForInvalidChecksum(String digits, List<String> possibleChecksum) {
        if (possibleChecksum.size() == 1) {
            return possibleChecksum.get(0);
        }
        final var status = possibleChecksum.size() > 1 ? " AMB " + possibleChecksum : " ILL";
        return digits + status;
    }
}
