package pl.studentmed.facultative.exceptions;

import static pl.studentmed.facultative.models.doctor.Specialization.*;

public class InvalidSpecializationException extends RuntimeException{

    public static final String MESSAGE = String.format("Specialization must be one of '%s', '%s', '%s' or '%s'.",
            INTERNIST.value, GASTROENTEROLOGIST.value, OPHTHALMOLOGIST.value, PULMONOLOGIST.value);

    public InvalidSpecializationException() {
        super(MESSAGE);
    }

}
