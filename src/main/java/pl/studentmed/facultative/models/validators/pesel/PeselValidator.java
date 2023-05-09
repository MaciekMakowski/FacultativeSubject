package pl.studentmed.facultative.models.validators.pesel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PeselValidator implements ConstraintValidator<Pesel, String> {

    @Override
    public boolean isValid(String pesel, ConstraintValidatorContext context) {
        if (pesel == null || pesel.length() != 11) {
            return false;
        }

        int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(pesel.charAt(i)) * weights[i];
        }
        int checksum = 10 - (sum % 10);
        if (checksum == 10) {
            checksum = 0;
        }

        if (checksum != Character.getNumericValue(pesel.charAt(10))) {
            return false;
        }

        String dateOfBirthString = pesel.substring(0, 6);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
            LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString, formatter);
            int year = dateOfBirth.getYear();
            int month = dateOfBirth.getMonthValue();
            int day = dateOfBirth.getDayOfMonth();

            boolean isYearValid = (year >= 1800 && year <= 2299);
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

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private int getDaysInMonth(int month, boolean isLeapYear) {
        return switch (month) {
            case 2 -> isLeapYear ? 29 : 28;
            case 4, 6, 9, 11 -> 30;
            default -> 31;
        };
    }

}
