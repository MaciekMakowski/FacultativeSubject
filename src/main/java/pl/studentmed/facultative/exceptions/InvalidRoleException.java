package pl.studentmed.facultative.exceptions;

import static pl.studentmed.facultative.models.user_info.Role.*;

public class InvalidRoleException extends RuntimeException {

    public static final String MESSAGE = String.format("Role must be one of '%s', '%s', or '%s'",
            PATIENT.value, DOCTOR.value, RECEPTION.value);

    public InvalidRoleException() {
        super(MESSAGE);
    }

}
