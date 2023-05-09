package pl.studentmed.facultative.models.validators.age;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class AdultAgeValidator implements ConstraintValidator<AdultAge, LocalDate> {
    private static final int ADULT_AGE = 18;

    @Override
    public void initialize(AdultAge constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        LocalDate currentDate = LocalDate.now();
        LocalDate adultDate = currentDate.minusYears(ADULT_AGE);

        return value.isBefore(adultDate);
    }

}
