package pl.studentmed.facultative.models.addresses;

import pl.studentmed.facultative.exceptions.EmptyFieldException;
import pl.studentmed.facultative.exceptions.InvalidLengthException;
import pl.studentmed.facultative.exceptions.InvalidZipCodeException;

public class ZipCode {

    private String zipCode;
    private final static int VALID_ZIPCODE_LENGTH = 6;
    private final static String DIGITS = "\\d";

    public ZipCode(String zipCode) {
        if (zipCode == null) {
            throw new EmptyFieldException("zipCode", "Zip code can't be empty.");
        }
        zipCode = zipCode.strip();
        if (zipCode.length() != VALID_ZIPCODE_LENGTH) {
            throw new InvalidLengthException("zipCode", "Zip code must contains 6 signs.");
        }
        if (!isValid(zipCode)) {
            throw new InvalidZipCodeException("Invalid zip code given.");
        }
        this.zipCode = zipCode;
    }

    private boolean isValid(String zipCode) {
        return zipCode.charAt(2) == '-' && consistOnlyDigits(zipCode);
    }

    private boolean consistOnlyDigits(String zipCode) {
        var firstTwoChars = zipCode.substring(0, 2);
        var lastThreeChars = zipCode.substring(3, 6);
        return firstTwoChars.matches(DIGITS) && lastThreeChars.matches(DIGITS);
    }

    public String value(){
        return this.zipCode;
    }

}
