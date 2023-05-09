package pl.studentmed.facultative.models.address.vo;

import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;
import pl.studentmed.facultative.exceptions.EmptyFieldException;
import pl.studentmed.facultative.exceptions.InvalidLengthException;
import pl.studentmed.facultative.exceptions.InvalidSignsException;

import static pl.studentmed.facultative.models.validators.ValueObject.containsLettersAndSpaces;

@Embeddable
@NoArgsConstructor
public class City {

    private String city;
    private final static int MAXIMUM_LENGTH = 45;

    public City(String city) {
        if (city == null || city.length() == 0) {
            throw new EmptyFieldException("city", "City name can't be empty.");
        }
        city = city.strip();
        if (city.length() > MAXIMUM_LENGTH) {
            throw new InvalidLengthException("city", "City name is too long.");
        }
        if (!containsLettersAndSpaces(city)) {
            throw new InvalidSignsException("city", "City name contains invalid characters.");
        }
        this.city = city;
    }

    public String value() {
        return this.city;
    }

}
