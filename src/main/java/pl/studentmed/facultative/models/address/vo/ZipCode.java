package pl.studentmed.facultative.models.address.vo;

import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;
import pl.studentmed.facultative.exceptions.EmptyFieldException;
import pl.studentmed.facultative.exceptions.InvalidZipCodeException;

@Embeddable
@NoArgsConstructor
public class ZipCode {

    private String zipCode;
    private static final String ZIPCODE_REGEX = "\\d{2}-\\d{3}";

    public ZipCode(String zipCode) {
        if (zipCode == null || zipCode.isEmpty()) {
            throw new EmptyFieldException("zipCode", "Zip code can't be empty.");
        }
        zipCode = zipCode.strip();
        if (!zipCode.matches(ZIPCODE_REGEX)) {
            throw new InvalidZipCodeException("Invalid zip code given.");
        }
        this.zipCode = zipCode;
    }

    public String value() {
        return this.zipCode;
    }

}
