package com.kata;

import java.util.List;
import java.util.stream.Collectors;

public class AccountNumberPresenter {

    public String present(AccountNumber accountNumber) {
        return present(
                accountNumber.digits(),
                accountNumber.checksum(),
                accountNumber.possibleCombinations());
    }

    private static String present(List<Integer> digits, boolean checksum, List<String> possibleChecksum) {
        if (isGuessedVariantCorrect(checksum, possibleChecksum)) {
            return possibleChecksum.get(0);
        }
        return presentDigits(digits) + getStatus(digits, checksum, possibleChecksum);
    }

    private static boolean isGuessedVariantCorrect(boolean checksum, List<String> possibleChecksum) {
        return !checksum && possibleChecksum.size() == 1;
    }

    private static String presentDigits(List<Integer> digits) {
        return digits.stream()
                .map(String::valueOf)
                .map(AccountNumberPresenter::replaceInvalidDigits)
                .collect(Collectors.joining());
    }

    private static String replaceInvalidDigits(String digit) {
        return digit.equals("-1") ? "?" : digit;
    }

    private static String getStatus(List<Integer> digits, boolean checksum, List<String> possibleChecksum) {
        if (digits.contains(-1)) {
            return " ILL";
        } else if (!checksum) {
            return possibleChecksum.size() > 1 ? " AMB " + possibleChecksum : " ILL";
        } else {
            return "";
        }
    }

}
