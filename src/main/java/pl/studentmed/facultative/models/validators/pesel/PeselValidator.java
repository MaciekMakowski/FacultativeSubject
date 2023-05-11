package pl.studentmed.facultative.models.validators.pesel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PeselValidator implements ConstraintValidator<Pesel, String> {

    private static final int[] WEIGHTS = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};

    @Override
    public boolean isValid(String pesel, ConstraintValidatorContext context) {
        if (pesel == null || pesel.length() != 11) {
            return false;
        }

        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Character.getNumericValue(pesel.charAt(i));
        }

        int checksum = calculateChecksum(digits);
        if (checksum != digits[10]) {
            return false;
        }

        int year = getYear(digits);
        int month = getMonth(digits);
        int day = getDay(digits);

        boolean isYearValid = (year >= 1900 && year <= 1999) || (year >= 2000 && year <= 2099);
        boolean isMonthValid = (month >= 1 && month <= 12);
        boolean isDayValid = (day >= 1 && day <= 31);

        if (!isYearValid || !isMonthValid || !isDayValid) {
            return false;
        }

        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        int daysInMonth = getDaysInMonth(month, isLeapYear);
        if (day > daysInMonth) {
            return false;
        }

        return true;
    }

    private int calculateChecksum(int[] digits) {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += digits[i] * WEIGHTS[i];
        }
        return (10 - (sum % 10)) % 10;
    }

    private int getYear(int[] digits) {
        int yearPrefix = digits[0] * 10 + digits[1];
        int month = digits[2] * 10 + digits[3];

        int year;
        if (month >= 81 && month <= 92) {
            year = 1800 + yearPrefix;
        } else if (month >= 1 && month <= 12) {
            year = 1900 + yearPrefix;
        } else if (month >= 21 && month <= 32) {
            year = 2000 + yearPrefix;
        } else if (month >= 41 && month <= 52) {
            year = 2100 + yearPrefix;
        } else if (month >= 61 && month <= 72) {
            year = 2200 + yearPrefix;
        } else {
            // Invalid month
            return -1;
        }

        return year;
    }

    private int getMonth(int[] digits) {
        int month = digits[2] * 10 + digits[3];

        if (month >= 1 && month <= 12) {
            return month;
        } else if (month >= 21 && month <= 32) {
            return month - 20;
        } else if (month >= 41 && month <= 52) {
            return month - 40;
        } else if (month >= 61 && month <= 72) {
            return month - 60;
        }

        return -1; // Invalid month
    }

    private int getDay(int[] digits) {
        int day = digits[4] * 10 + digits[5];

        if (day >= 1 && day <= 31) {
            return day;
        } else if (day >= 41 && day <= 71) {
            return day - 40;
        }

        return -1; // Invalid day
    }

    private int getDaysInMonth(int month, boolean isLeapYear) {
        switch (month) {
            case 2:
                return isLeapYear ? 29 : 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }

}
