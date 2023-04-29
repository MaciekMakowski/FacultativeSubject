package pl.studentmed.facultative.exceptions;

public class AppointmentDateAlreadyTakenException extends RuntimeException {

    public final String fieldName;

    public AppointmentDateAlreadyTakenException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

}
