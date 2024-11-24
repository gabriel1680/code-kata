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
        if (isGuessedVariantCorrect(checksum, possibleChecksum)) {
            return possibleChecksum.get(0);
        }
        return digits + getStatus(digits, checksum, possibleChecksum);
    }

    private static boolean isGuessedVariantCorrect(boolean checksum, List<String> possibleChecksum) {
        return !checksum && possibleChecksum.size() == 1;
    }

    private static String getStatus(String digits, boolean checksum, List<String> possibleChecksum) {
        if (digits.contains("?")) {
            return " ILL";
        } else if (!checksum) {
            return possibleChecksum.size() > 1 ? " AMB " + possibleChecksum : " ILL";
        } else {
            return "";
        }
    }

}
